package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.UUID;
import com.example.kakaotest.component.temp;
import com.fortanix.sdkms.v1.ApiClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class Step2ItemProcessor implements ItemProcessor<UUID, List<HashMap>>, StepExecutionListener {

//    private DataShareBean<ApiClient> dataShareBean = new DataShareBean();

    private ApiClient apiClient;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.apiClient = temp.apiClient;
//        this.apiClient = dataShareBean.getData("ApiClient");
    }

    @Override
    public List<HashMap> process(UUID uuid) throws Exception {

        List<HashMap> mailObjects = new ArrayList();

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> BearerTokenResponse = Unirest.post("https://sdkms.fortanix.com/sys/v1/session/auth")
                .header("Authorization", "Basic Mzg0ZDRiMzYtYmJlZS00MDhmLTkxNzQtNmI0NWQ3ZGQwOTc3OmlEX281TnNnZ1FMTnlqWDhBU1lOeG95MjBqNFZ1OUFlWkRFYi0yVldfajYxWGZjaW93OUVOanN1U1VmMWtlaEZ3SE9lT0d5THV2RzUybDlaMGl5TXB3")
                .asJson();

        String accessToken = BearerTokenResponse.getBody().getObject().getString("access_token");

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> keyObjectsResponse = Unirest.get("https://sdkms.fortanix.com/crypto/v1/keys?group_id=" + uuid.getUUID())
                .header("Authorization", "Bearer " + accessToken)
                .asJson();

        JSONArray keyObjectArray = keyObjectsResponse.getBody().getArray();

        for (int i = 0; i < keyObjectArray.length(); i++) {

            JSONObject keyObject = keyObjectArray.getJSONObject(i);
            System.out.println(keyObject.getString("name"));

            try {

                String deactivationDate = keyObject.getString("deactivation_date");
                System.out.println(deactivationDate);
                LocalDateTime d = LocalDateTime.parse(deactivationDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                LocalDateTime currentTime = LocalDateTime.now();
                ZoneId zoneId = ZoneId.systemDefault();
                long dateLeftForDeactivation = (d.atZone(zoneId).toEpochSecond() - currentTime.atZone(zoneId).toEpochSecond()) / 86400;
                System.out.println("deactivate time epoch: " + d.atZone(zoneId).toEpochSecond());
                System.out.println("current time epoch: " + currentTime.atZone(zoneId).toEpochSecond());
                System.out.println("dateLeftForDeactivation: " + dateLeftForDeactivation);
                if(isKeyToBeMailed(dateLeftForDeactivation)) {
                    mailObjects.add(new HashMap<String,String>(){{
                        put("name", keyObject.getString("name"));
                        put("deactivate_date", String.valueOf(deactivationDate));
                        put("acct_id", keyObject.getString("acct_id"));
                    }});
                }

            } catch (Exception e) {
                System.out.println("deactivation null");
            }

        }


        log.info(">>>>>>working");
        return mailObjects;
    }


    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    private boolean isKeyToBeMailed(long dateLeftForDeactivation) {
        if (dateLeftForDeactivation == 30 || dateLeftForDeactivation == 7 || dateLeftForDeactivation == 1)
            return true;
        else
            return false;
    }
}
