package com.acme.apolice.core.ports.driven.kafka;

public interface ApoliceEventHandler {
    void handle(String payload);
}
