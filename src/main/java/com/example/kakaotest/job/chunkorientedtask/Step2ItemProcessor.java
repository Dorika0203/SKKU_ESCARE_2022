package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.UUID;
import com.example.kakaotest.component.temp;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.api.GroupsApi;
import com.fortanix.sdkms.v1.api.SecurityObjectsApi;
import com.fortanix.sdkms.v1.model.Group;
import com.fortanix.sdkms.v1.model.KeyObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@Slf4j
public class Step2ItemProcessor implements ItemProcessor<UUID, String>, StepExecutionListener {

//    private DataShareBean<ApiClient> dataShareBean = new DataShareBean();

    private ApiClient apiClient;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.apiClient = temp.apiClient;
//        this.apiClient = dataShareBean.getData("ApiClient");
    }

    @Override
    public String process(UUID uuid) throws Exception {
        GroupsApi groupsApi = new GroupsApi(apiClient);
        Group group = groupsApi.getGroup(uuid.getUUID());
        SecurityObjectsApi securityObjectsApi = new SecurityObjectsApi(apiClient);
        System.out.println(group.getName());
        System.out.println(group.getGroupId());
        System.out.println(group.getCreator().getUser());
        List<KeyObject> keyObjects = securityObjectsApi.getSecurityObjects(null,group.getGroupId(),null);
        keyObjects.forEach((temp)->{
           System.out.println(temp.getName());
        });
        log.info(">>>>>>working");
        return "hi";
    }



    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

}
