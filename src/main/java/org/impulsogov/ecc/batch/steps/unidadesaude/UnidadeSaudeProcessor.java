package org.impulsogov.ecc.batch.steps.unidadesaude;

import org.impulsogov.ecc.models.UnidadeSaude;
import org.impulsogov.ecc.services.UnidadeSaudeService;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UnidadeSaudeProcessor implements ItemProcessor<UnidadeSaude, UnidadeSaude> {

    @Autowired
    private UnidadeSaudeService service;
    
    @Override
    public UnidadeSaude process(UnidadeSaude item) throws Exception {
        return null;
    }
}
