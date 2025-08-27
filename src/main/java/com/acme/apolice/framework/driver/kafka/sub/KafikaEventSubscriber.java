package com.acme.apolice.framework.driver.kafka.sub;

import com.acme.apolice.adapter.outbound.impl.ApoliceEventDto;
import com.acme.apolice.core.usecase.ClienteUseCase;
import com.acme.apolice.core.usecase.EventApoliceUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafikaEventSubscriber {

    private final EventApoliceUseCase useCase;

    public KafikaEventSubscriber(EventApoliceUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "apolice.enquadramento", groupId = "apolice-group")
    public void listen(ApoliceEventDto message) {
        useCase.executar(message);
    }
}
