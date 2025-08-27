package com.acme.apolice.adapter.outbound.impl;

import com.acme.apolice.adapter.inbound.Estado;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ApoliceEventDto(UUID apoliceId,
                              Estado estado,
                              OffsetDateTime dataSolicitacao) {
}
