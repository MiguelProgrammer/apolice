package com.acme.apolice.infrastructure.adapter;

import com.acme.apolice.core.domain.enums.TipoCliente;
import com.acme.apolice.core.ports.ApoliceRepositoryPort;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.ClienteEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.OcorrenciaEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;
import com.acme.apolice.infrastructure.database.postgresql.apolice.repositories.ApoliceRepository;
import com.acme.apolice.infrastructure.database.postgresql.apolice.repositories.ClienteRepository;
import com.acme.apolice.infrastructure.database.postgresql.apolice.repositories.OcorrenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class ApoliceRepositoryAdapter implements ApoliceRepositoryPort {

    private final ApoliceRepository apoliceRepository;
    private final ClienteRepository clienteRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public ApoliceRepositoryAdapter(ApoliceRepository apoliceRepository, ClienteRepository clienteRepository, OcorrenciaRepository ocorrenciaRepository) {
        this.apoliceRepository = apoliceRepository;
        this.clienteRepository = clienteRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @Override
    @Transactional
    public ApoliceEntity save(ApoliceEntity apoliceEntity) {

        ApoliceEntity apolice = apoliceRepository.save(apoliceEntity);
        apolice.setApoliceId(apolice.getId());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setClienteId(apolice.getClienteId());
        cliente.setApoliceId(apolice.getApoliceId());
        cliente.setDataAnalise(apolice.getDataCriacao());
        cliente.setClassificacao(TipoCliente.determinarTipo(apoliceEntity.getValorSegurado(), List.of(apoliceEntity.getCategoria())));

        Set<OcorrenciaEntity> ocorrencias = new HashSet<>();
        OcorrenciaEntity ocorrencia = new OcorrenciaEntity();
        ocorrencia.setDataCriacao(apolice.getDataCriacao());
        ocorrencia.setProdutoId(apolice.getProdutoId());
        ocorrencia.setTipo(cliente.getClassificacao());
        ocorrencia.setDescricao(cliente.getClassificacao().name());
        cliente.setOccurrencias(ocorrencias);
        ocorrencia.setCliente(cliente);
        ocorrencias.add(ocorrencia);

        clienteRepository.save(cliente);
        ocorrenciaRepository.save(ocorrencia);
        return apolice;
    }

    @Override
    public ApoliceConsultaProjection listaApolice(UUID id) {
        return apoliceRepository.findByApolice(id);
    }

}
