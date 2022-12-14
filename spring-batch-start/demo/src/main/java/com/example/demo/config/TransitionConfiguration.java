package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TransitionConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

        @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                    .on("FAILED")
                    .to(step2())
                    .on("FAILED")
                    .stop()
                .from(step1())
                    .on("*")
                    .to(step3())
                    .next(step4())
                .from(step2())
                    .on("*")
                    .to(step5())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 1 was executed");
                    System.out.println("=========================");
//                    stepContribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 2 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 3 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 4 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("=========================");
                    System.out.println(" >> step 5 was executed");
                    System.out.println("=========================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}

