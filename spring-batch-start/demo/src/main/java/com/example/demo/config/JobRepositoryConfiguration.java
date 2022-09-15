package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobRepositoryConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

//    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .start(batchStep1())
                .next(batchStep2())
                .listener(jobExecutionListener)
                .build();
    }

//    @Bean
    public Step batchStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 1 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
    public Step batchStep2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 2 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
