package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Bean
    public Job jobInstanceJob() {
        return jobBuilderFactory.get("job")
                .start(jobInstanceStep1())
                .next(jobInstanceStep2())
                .build();
    }

//    @Bean
    public Step jobInstanceStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
    public Step jobInstanceStep2() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
