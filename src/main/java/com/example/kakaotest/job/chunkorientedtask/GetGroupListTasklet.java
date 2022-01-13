package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.mail.ExternalEnv;
import com.example.kakaotest.model.GroupUUID;
import com.example.kakaotest.repository.GroupUUIDRepo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class GetGroupListTasklet implements Tasklet {

    @Autowired
    private ExternalEnv env;
    @Autowired
    private GroupUUIDRepo groupUUIDRepo;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String endpointURL = "https://sdkms.fortanix.com";

        //bearer token get
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> BearerTokenResponse = Unirest.post("https://sdkms.fortanix.com/sys/v1/session/auth")
                .header("Authorization", "Basic " + env.getApiKey())
                .asJson();
        String accessToken = BearerTokenResponse.getBody().getObject().getString("access_token");

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> jsonGroupListResponse = Unirest.get(endpointURL+"/sys/v1/groups")
                .header("Authorization", "Bearer " + accessToken).asJson();
        JSONArray jsonGroupList = jsonGroupListResponse.getBody().getArray();

        List<GroupUUID> fromHSM = new ArrayList<GroupUUID>();
        List<GroupUUID> fromDB = groupUUIDRepo.findAllByOrderByGuid();

        for(int i=0; i<jsonGroupList.length(); i++) {
            JSONObject obj = (JSONObject) jsonGroupList.get(i);
            GroupUUID tmp = new GroupUUID();
            tmp.setGroup_name((String) obj.get("name"));
            tmp.setGuid((String) obj.get("group_id"));
            fromHSM.add(tmp);
        }
        fromHSM.sort(compareByGroupID);

        int i = 0, j = 0;
        for(i=0; i<fromHSM.size(); i++) {
            GroupUUID tmp1 = fromHSM.get(i);
            if (j >= fromDB.size()) {
                groupUUIDRepo.saveAndFlush(tmp1);
                continue;
            }
            GroupUUID tmp2 = fromDB.get(j);
            if (compareByGroupID.compare(tmp1, tmp2) == 0) {
                j++;
            }
            else if(compareByGroupID.compare(tmp1, tmp2) < 0) {
                groupUUIDRepo.saveAndFlush(tmp1);
            }
            else {
                groupUUIDRepo.delete(tmp2);
                i--;
                j++;
            }
        }
        return RepeatStatus.FINISHED;
    }

    private Comparator<GroupUUID> compareByGroupID = new Comparator<GroupUUID>() {
        @Override
        public int compare(GroupUUID o1, GroupUUID o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getGuid(), o2.getGuid());
        }
    };
}
