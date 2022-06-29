package org.impulsogov.ecc.batch.steps.unidadesaude;

import org.impulsogov.ecc.models.UnidadeSaude;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnidadeSaudeStepConfiguration {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public UnidadeSaudeReader unidadeSaudeReaderStep() {
        return new UnidadeSaudeReader();
    }

    @Bean
    public UnidadeSaudeWriter unidadeSaudeWriterStep() {
        return new UnidadeSaudeWriter();
    }

    @Bean
    public Step stepUnidadeSaude() {
        return this.stepBuilderFactory.get("StepUnidadeSaude")
            .<UnidadeSaude, UnidadeSaude> chunk(1)
                .reader(this.unidadeSaudeReaderStep())
                    .writer(this.unidadeSaudeWriterStep())
                        .allowStartIfComplete(true)
                            .build();
    }
}
