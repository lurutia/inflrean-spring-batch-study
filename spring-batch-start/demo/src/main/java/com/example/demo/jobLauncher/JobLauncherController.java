package com.example.demo.jobLauncher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@RestController
@RequiredArgsConstructor
public class JobLauncherController {
    private final Job job;
    private final JobLauncher simpleJobLauncher;
    private final BasicBatchConfigurer basicBatchConfigurer;

//    @PostMapping("/batch")
    public String launcher(
//            @RequestBody
            Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        // 비동기
        SimpleJobLauncher jobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

        // 동기
//        SimpleJobLauncher jobLauncher = (SimpleJobLauncher)simpleJobLauncher;
        jobLauncher.run(job, jobParameters);

        return "batch completed";
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class Member {
        private String id;
    }
}
