package com.acme.apolice.framework.driver.kafka.sub;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.outbound.impl.ApoliceEventDto;
import com.acme.apolice.core.usecase.ApoliceUseCase;
import com.acme.apolice.core.usecase.EventApoliceUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafikaEventSubscriber {

    public static final String APOLICE_ENQUADRAMENTO_TOPIC = "apolice.enquadramento";
    public static final String APOLICE_GROUP = "apolice-group";
    private final EventApoliceUseCase useCase;
    private final ApoliceUseCase apoliceUseCase;

    public KafikaEventSubscriber(EventApoliceUseCase useCase, ApoliceUseCase apoliceUseCase) {
        this.useCase = useCase;
        this.apoliceUseCase = apoliceUseCase;
    }

    @KafkaListener(topics = APOLICE_ENQUADRAMENTO_TOPIC, groupId = APOLICE_GROUP)
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ApoliceEventDto eventDto = objectMapper.readValue(message, ApoliceEventDto.class);
        ApoliceConsulta apoliceConsulta = apoliceUseCase.apoliceDetalhada(eventDto.getApoliceId());
        useCase.processarApolice(apoliceConsulta);
    }
}
