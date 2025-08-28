package com.acme.apolice.framework.web.resource;

import com.acme.apolice.adapter.inbound.impl.rest.FraudeRestAdapter;
import com.acme.fraude.AnaliseFraudeRequest;
import com.acme.fraude.AnaliseFraudeResponse;
import com.acme.fraude.controller.V1Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/analise")
public class FraudeResource /*implements V1Api*/ {

    @Autowired
    private FraudeRestAdapter fraudeRestAdapter;

    /**
     * POST /v1/analise : Realiza a análise de risco de um cliente
     * Realiza a análise de risco do cliente com base no ID da apólice e do cliente.
     *
     * @param analiseFraudeRequest (required)
     * @return Análise de risco realizada com sucesso (status code 200)
     * or Requisição inválida (status code 400)
     */

    @PostMapping
    public ResponseEntity<AnaliseFraudeResponse> analisarRisco(AnaliseFraudeRequest analiseFraudeRequest) {
        AnaliseFraudeResponse analiseFraudeResponse = fraudeRestAdapter.analisarRisco(analiseFraudeRequest);
        return ResponseEntity.ok(analiseFraudeResponse);
    }
}
