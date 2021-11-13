package com.example.kakaotest.component;

import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionControl implements HttpSessionListener {

    // 세션 생성시 호출
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.println("[ Session Created.. ]");
        System.out.println("session ID: " + session.getId());

        // 의존성 주입
        WebApplicationContextUtils.getRequiredWebApplicationContext(event.getSession().getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.println("[ Session Delete Start! ]");
        System.out.println("session ID: " + session.getId());
        System.out.println("[ Session Delete Done .. ]");
    }
}
