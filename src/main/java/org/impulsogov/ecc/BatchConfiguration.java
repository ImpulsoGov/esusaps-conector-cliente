package org.impulsogov.ecc;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public JobBuilderFactory jobBuilderFactory;
    private Step stepUnidadeSaude;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, Step stepUnidadeSaude) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepUnidadeSaude = stepUnidadeSaude;
    }

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("impulsogov")
            .flow(this.stepUnidadeSaude)
            .end()
            .build();
    }

}
