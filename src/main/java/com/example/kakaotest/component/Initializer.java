package com.example.kakaotest.component;


import com.example.kakaotest.model.AdminModel;
import com.example.kakaotest.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import static org.apache.commons.codec.digest.DigestUtils.sha256;

@Component
@AllArgsConstructor
public class Initializer implements ApplicationListener<ContextRefreshedEvent>{

    AdminRepository adminRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("SERVER INIT START...");

        // 기본 수퍼 관리자 생성.
        String id = "admin";
        AdminModel superAdmin = new AdminModel(id, sha256(id.getBytes()));
        adminRepository.saveAndFlush(superAdmin);

        System.out.println("SERVER INIT FINISHED !");
    }

}