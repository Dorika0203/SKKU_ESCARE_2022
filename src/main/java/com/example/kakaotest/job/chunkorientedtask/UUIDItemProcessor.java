package com.example.kakaotest.job.chunkorientedtask;

import com.example.kakaotest.component.mail.MailDto;
import com.example.kakaotest.model.GroupUUIDModel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UUIDItemProcessor implements ItemProcessor<GroupUUIDModel, List<MailDto>> {

    private String titleTemplate = "REMINDER: Group UUID: %s key name: %s's deactivate date is %s left";
    private long dateLeftForDeactivation;

    @Override
    public List<MailDto> process(GroupUUIDModel uuid) throws Exception {

        List<MailDto> mailObjects = new ArrayList();

        //bearer token get
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> BearerTokenResponse = Unirest.post("https://sdkms.fortanix.com/sys/v1/session/auth")
                .header("Authorization", "Basic Mzg0ZDRiMzYtYmJlZS00MDhmLTkxNzQtNmI0NWQ3ZGQwOTc3OmlEX281TnNnZ1FMTnlqWDhBU1lOeG95MjBqNFZ1OUFlWkRFYi0yVldfajYxWGZjaW93OUVOanN1U1VmMWtlaEZ3SE9lT0d5THV2RzUybDlaMGl5TXB3")
                .asJson();

        String accessToken = BearerTokenResponse.getBody().getObject().getString("access_token");

        //auth and get keys
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> keyObjectsResponse = Unirest.get("https://sdkms.fortanix.com/crypto/v1/keys?group_id=" + uuid.getUuid())
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
                            uuid.getMailAddress(),
                            String.format(titleTemplate,
                                    uuid.getUuid(),
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
        if (dateLeftForDeactivation == 30 || dateLeftForDeactivation == 7 || dateLeftForDeactivation == 1)
            return true;
        else
            return false;
    }
}
