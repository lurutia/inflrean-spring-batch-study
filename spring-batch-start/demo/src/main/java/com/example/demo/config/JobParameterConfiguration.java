package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
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
public class JobParameterConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Bean
    public Job jobParameterJob() {
        return jobBuilderFactory.get("job")
                .start(jobParameterStep1())
                .next(jobParameterStep2())
                .build();
    }

//    @Bean
    public Step jobParameterStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    JobParameters jobParameters = stepContribution.getStepExecution().getJobExecution().getJobParameters();
                    String name = jobParameters.getString("name");
                    Long seq = jobParameters.getLong("seq");
                    Date date = jobParameters.getDate("date");
                    double age = jobParameters.getDouble("age");

                    Map<String, Object> jobParameter1 = chunkContext.getStepContext().getJobParameters();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
    public Step jobParameterStep2() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
