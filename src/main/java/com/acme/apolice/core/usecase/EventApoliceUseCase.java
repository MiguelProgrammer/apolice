package com.acme.apolice.core.usecase;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.inbound.CategoriaSeguro;
import com.acme.apolice.adapter.inbound.Estado;
import com.acme.apolice.adapter.inbound.Historico;
import com.acme.apolice.adapter.outbound.ClienteMapper;
import com.acme.apolice.adapter.outbound.OcorrenciaMapper;
import com.acme.apolice.adapter.outbound.impl.ApoliceEventDto;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import com.acme.apolice.core.domain.cliente.ClienteDomain;
import com.acme.apolice.core.domain.cliente.OcorrenciaDomain;
import com.acme.apolice.core.domain.enums.TipoCliente;
import com.acme.apolice.core.ports.ApoliceRepositoryPort;
import com.acme.apolice.core.ports.driven.kafka.EventPublisher;
import com.acme.apolice.core.usecase.exception.ApoliceUseCaseBusinessException;
import com.acme.apolice.core.usecase.exception.EventUseCaseBusinessException;
import com.acme.apolice.infrastructure.adapter.outbound.ApoliceOutMapperInfra;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class EventApoliceUseCase {

    public static final String APOLICE_ENQUADRAMENTO_TOPIC = "apolice.enquadramento";
    private final EventPublisher eventPublisher;
    private final ObjectMapper objectMapper;
    private final ClienteMapper clienteMapper;
    private final OcorrenciaMapper ocorrenciaMapper;
    private final ApoliceRepositoryPort apoliceAdapter;
    private final ApoliceUseCase apoliceUseCase;
    private final ApoliceOutMapperInfra inMapper;

    public EventApoliceUseCase(EventPublisher eventPublisher,
                               ObjectMapper objectMapper, ClienteMapper clienteMapper, OcorrenciaMapper ocorrenciaMapper, ApoliceRepositoryPort apoliceAdapter,
                               ApoliceUseCase apoliceUseCase, ApoliceOutMapperInfra inMapper) {
        this.eventPublisher = eventPublisher;
        this.objectMapper = objectMapper;
        this.clienteMapper = clienteMapper;
        this.ocorrenciaMapper = ocorrenciaMapper;
        this.apoliceAdapter = apoliceAdapter;
        this.apoliceUseCase = apoliceUseCase;
        this.inMapper = inMapper;
    }

    public void executar(ApoliceDomain command) {

        ApoliceConsulta apoliceConsulta = apoliceUseCase.apoliceDetalhada(command.getId());
        /**
         * consulta api de fraudes
         */
        processarApolice(apoliceConsulta);
    }

    private void publicaApolice(ApoliceEventDto eventDto) {
        try {
            eventPublisher.publish(
                    APOLICE_ENQUADRAMENTO_TOPIC,
                    eventDto.getApoliceId().toString(),
                    objectMapper.writeValueAsString(eventDto));

        } catch (ApoliceUseCaseBusinessException | JsonProcessingException e) {
            throw new ApoliceUseCaseBusinessException(e.getMessage());
        }
    }

    public void processarApolice(ApoliceConsulta apoliceConsulta) {

        try {
            switch (apoliceConsulta.getStatus()) {
                case RECEBIDO ->
                        registraOcorrencia(apoliceConsulta, validaCliente(apoliceConsulta) ? Estado.VALIDADO : Estado.REJEITADO);
                case PENDENTE ->
                        registraOcorrencia(apoliceConsulta, validaCliente(apoliceConsulta) ? Estado.APROVADO : Estado.REJEITADO);
                case VALIDADO ->
                        registraOcorrencia(apoliceConsulta, validaCliente(apoliceConsulta) ? Estado.PENDENTE : Estado.REJEITADO);
                case APROVADO, REJEITADO, CANCELADA ->
                        System.out.println("Ação não permitida. A apólice " + apoliceConsulta.getId() + " já está em um estado final: " + apoliceConsulta.getStatus());
                default -> {
                }
            }
        } catch (EventUseCaseBusinessException e){
            throw new EventUseCaseBusinessException(e.getMessage());
        }
    }

    private void registraOcorrencia(ApoliceConsulta apoliceConsulta, Estado estado) {
        ClienteDomain clienteDomain = new ClienteDomain();
        clienteDomain.setClienteId(apoliceConsulta.getClienteId());
        clienteDomain.setApoliceId(apoliceConsulta.getId());
        clienteDomain.setDataAnalise(OffsetDateTime.now());
        clienteDomain.setClassificacao(obtemRisco(apoliceConsulta));

        OcorrenciaDomain ocorrenciaDomain = new OcorrenciaDomain();
        ocorrenciaDomain.setDataAtualizacao(clienteDomain.getDataAnalise());
        ocorrenciaDomain.setProdutoId(apoliceConsulta.getProdutoId());
        ocorrenciaDomain.setTipo(clienteDomain.getClassificacao());
        ocorrenciaDomain.setDescricao(obtemRisco(apoliceConsulta).name());
        clienteDomain.getOccurrencias().add(ocorrenciaDomain);

        clienteMapper.domainToInfra(clienteDomain);
        ocorrenciaMapper.domainToInfra(ocorrenciaDomain);

        Historico historico = new Historico();
        historico.setStatus(estado);
        historico.setDataCriacao(OffsetDateTime.now());
        apoliceConsulta.getHistorico().add(historico);

        ApoliceConsulta apoliceConsulta1 = apoliceUseCase.apoliceDetalhada(apoliceConsulta.getId());
        ApoliceEntity entity = inMapper.domainConsultaToEntity(apoliceConsulta1);


        /**
         * ATUALIZA
         */
        apoliceAdapter.save(entity);

        /**
         * PUBLICA
         */
        publicaApolice(new ApoliceEventDto(entity.getId(),
                historico.getStatus(), historico.getDataCriacao()));
    }

    /**
     * INTERNAL SERVICE
     *
     * @param apoliceConsulta
     * @return
     */
    private Boolean validaCliente(ApoliceConsulta apoliceConsulta) {
        return TipoCliente.REGULAR.validar(
                apoliceConsulta.getValorSegurado(),
                List.of(CategoriaSeguro.valueOf(apoliceConsulta.getCategoria().name())));
    }

    /**
     * INTERNAL SERVICE
     *
     * @param apoliceConsulta
     * @return
     */
    private TipoCliente obtemRisco(ApoliceConsulta apoliceConsulta) {
        return TipoCliente.determinarTipo(apoliceConsulta.getValorSegurado(),
                Collections.singletonList(apoliceConsulta.getCategoria()));
    }
}
