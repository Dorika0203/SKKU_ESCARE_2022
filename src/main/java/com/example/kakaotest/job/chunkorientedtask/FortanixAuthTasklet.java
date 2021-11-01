package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.DataShareBean;
import com.example.kakaotest.component.temp;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.api.AuthenticationApi;
import com.fortanix.sdkms.v1.auth.ApiKeyAuth;
import com.fortanix.sdkms.v1.model.AuthResponse;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@StepScope
public class FortanixAuthTasklet implements Tasklet {


//    DataShareBean<ApiClient> dataShareBean = new DataShareBean();

    private static final String ApiKey = "Mzg0ZDRiMzYtYmJlZS00MDhmLTkxNzQtNmI0NWQ3ZGQwOTc3OmlEX281TnNnZ1FMTnlqWDhBU1lOeG95MjBqNFZ1OUFlWkRFYi0yVldfajYxWGZjaW93OUVOanN1U1VmMWtlaEZ3SE9lT0d5THV2RzUybDlaMGl5TXB3";

    private static final String Server = "https://sdkms.fortanix.com";

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
        //for temporary
        temp.apiClient = apiClient;
//        dataShareBean.putData("ApiClient", apiClient);
        return RepeatStatus.FINISHED;
    }

}
