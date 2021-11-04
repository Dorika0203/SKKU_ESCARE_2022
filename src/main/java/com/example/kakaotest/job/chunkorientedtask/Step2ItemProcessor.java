package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.UUID;
import com.example.kakaotest.component.temp;
import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.ApiException;
import com.fortanix.sdkms.v1.api.GroupsApi;
import com.fortanix.sdkms.v1.api.SecurityObjectsApi;
import com.fortanix.sdkms.v1.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.Arrays;
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
        System.out.println("Group name: " + group.getName());
        System.out.println("Group ID: " + group.getGroupId());
        System.out.println("Group Users: " + group.getCreator().getUser());
        List<KeyObject> keyObjects = securityObjectsApi.getSecurityObjects(null, group.getGroupId(), null);

        //이거 존나 터짐 keyobject에서 deprecation date 찾아야함 ㅅㅂ
        //        keyObjects.forEach((keyObject) -> {
//            keyObject.getHistory().forEach((history)->{
//                System.out.println("expire: " + history.getExpiry());
//            });
//            System.out.println("key name: " + keyObject.getName() + " keyDeactivationDate: " + "temp");
//        });
        log.info(">>>>>>working");
        return "hi";
    }


    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

}
