package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.mail.ExternalEnv;
import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.model.Group2Email;
import com.example.kakaotest.model.GroupUUIDModel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class UUIDItemProcessor implements ItemProcessor<Group2Email, List<MailDto>> {

    private String titleTemplate = "REMINDER: Group UUID: %s key name: %s's deactivate date is %s left";
    private long dateLeftForDeactivation;
    @Autowired
    private ExternalEnv env;

    @Override
    public List<MailDto> process(Group2Email uuid) throws Exception {

        List<MailDto> mailObjects = new ArrayList();

        //bearer token get

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> BearerTokenResponse = Unirest.post("https://sdkms.fortanix.com/sys/v1/session/auth")
                .header("Authorization", "Basic " + env.getApiKey())
                .asJson();
        String accessToken = BearerTokenResponse.getBody().getObject().getString("access_token");


        // Get Keys from corresponding group
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> keyObjectsResponse1 = Unirest.get("https://sdkms.fortanix.com/crypto/v1/keys?group_id=" + uuid.getGuid().getGuid())
                .header("Authorization", "Bearer " + accessToken)
                .asJson();
        System.out.println(uuid.getGuid().getGuid());
        System.out.println(keyObjectsResponse1.getBody());


        // Get Keys from corresponding group
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> keyObjectsResponse = Unirest.get("https://sdkms.fortanix.com/crypto/v1/keys?group_id=" + uuid.getGuid().getGuid())
                .header("Authorization", "Bearer " + accessToken)
                .asJson();
        JSONArray keyObjectArray = keyObjectsResponse.getBody().getArray();


        for (int i = 0; i < keyObjectArray.length(); i++) {

            JSONObject keyObject = keyObjectArray.getJSONObject(i);
            System.out.println(keyObject.getString("name"));

            try {

                String deactivationDate = keyObject.getString("deactivation_date");
                LocalDateTime deactivateTime = LocalDateTime.parse(deactivationDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                LocalDateTime currentTime = LocalDateTime.now();
                ZoneId zoneId = ZoneId.systemDefault();
                dateLeftForDeactivation = (long) Math.ceil((double)(deactivateTime.atZone(zoneId).toEpochSecond() - currentTime.atZone(zoneId).toEpochSecond()) / 86400);
                System.out.println("dateLeftForDeactivation: " + dateLeftForDeactivation);
            } catch (Exception e) {
                System.out.println("deactivation null");
            }
            try {
                if (isKeyToBeMailed(dateLeftForDeactivation)) {
                    mailObjects.add(new MailDto(
                            uuid.getEmail().getEmail(),
                            String.format(titleTemplate,
                                    uuid.getGuid().getGuid(),
                                    keyObject.getString("name"),
                                    dateLeftForDeactivation
                            ),
                            String.format("Key expire date: %s you should rekey after the date the key becomes deactivated \nmessage is sent automatically", keyObject.getString("deactivation_date"))
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        log.info(">>>>>>working");
        return mailObjects;
    }

    private boolean isKeyToBeMailed(long dateLeftForDeactivation) {
//        if (dateLeftForDeactivation == 30 || dateLeftForDeactivation == 7 || dateLeftForDeactivation == 1)
        if (dateLeftForDeactivation < 60)
            return true;
        else
            return false;
    }
}