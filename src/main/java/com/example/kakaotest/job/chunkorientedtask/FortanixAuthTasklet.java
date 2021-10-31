package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.job.stepexecution.SuperStepExecution;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.api.AuthenticationApi;
import com.fortanix.sdkms.v1.auth.ApiKeyAuth;
import com.fortanix.sdkms.v1.model.AuthResponse;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FortanixAuthTasklet extends SuperStepExecution<ApiClient> implements Tasklet, StepExecutionListener {

    private static final String ApiKey = "Mzg0ZDRiMzYtYmJlZS00MDhmLTkxNzQtNmI0NWQ3ZGQwOTc3OmlEX281TnNnZ1FMTnlqWDhBU1lOeG95MjBqNFZ1OUFlWkRFYi0yVldfajYxWGZjaW93OUVOanN1U1VmMWtlaEZ3SE9lT0d5THV2RzUybDlaMGl5TXB3";

    private static final String Server = "https://sdkms.fortanix.com";

    @Override
    public void beforeStep(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(Server);
        apiClient.setBasicAuthString(ApiKey);
        AuthenticationApi authenticationApi = new AuthenticationApi(apiClient);
        AuthResponse authResponse = authenticationApi.authorize();
        ApiKeyAuth bearerTokenAuth =
                (ApiKeyAuth) apiClient.getAuthentication("bearerToken");
        bearerTokenAuth.setApiKey(authResponse.getAccessToken());
        bearerTokenAuth.setApiKeyPrefix("Bearer");
        super.putData("ApiClient", apiClient);
        return  RepeatStatus.FINISHED;
    }



    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
