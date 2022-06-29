package org.impulsogov.ecc.batch.steps.unidadesaude;

import org.impulsogov.ecc.models.UnidadeSaude;
import org.impulsogov.ecc.services.UnidadeSaudeService;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UnidadeSaudeWriter implements ItemWriter<UnidadeSaude> {

    @Autowired
    private UnidadeSaudeService service;

    @Override
    public void write(List<? extends UnidadeSaude> items) throws Exception {
        for(UnidadeSaude unidadeSaude : items) {
            this.service.updateEnviadoImpulso(unidadeSaude.getId());
            this.service.transmit(unidadeSaude);
        }
    }
}
