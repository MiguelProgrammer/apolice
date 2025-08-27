package com.acme.apolice.core.ports.driven.rest;

import com.acme.fraude.AnaliseFraudeRequest;
import com.acme.fraude.AnaliseFraudeResponse;

public interface FraudeRestTemplate {

    AnaliseFraudeResponse analisarRisco(AnaliseFraudeRequest request);
}
