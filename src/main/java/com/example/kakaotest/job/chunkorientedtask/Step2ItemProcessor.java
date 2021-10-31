package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.job.stepexecution.SuperStepExecution;
import com.fortanix.sdkms.v1.ApiClient;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class Step2ItemProcessor extends SuperStepExecution<ApiClient> implements ItemProcessor<String, String>, StepExecutionListener {

    private ApiClient apiClient;

    @Override
    public void beforeStep(StepExecution stepExecution) {

            super.setStepExecution(stepExecution);
            this.apiClient = (ApiClient) super.getData("ApiClient");

    }

    @Override
    public String process(String item) throws Exception {

        return null;
    }



    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
