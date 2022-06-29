package org.impulsogov.ecc.batch.steps.unidadesaude;

import org.impulsogov.ecc.models.UnidadeSaude;
import org.impulsogov.ecc.services.UnidadeSaudeService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnidadeSaudeReader implements ItemReader<UnidadeSaude> {

    @Autowired
    private UnidadeSaudeService service;

    @Override
    public UnidadeSaude read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return this.service.getUnidadeSaudeNaoEnviada();
    }
}
