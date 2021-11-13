package com.example.kakaotest.component;

import javax.servlet.http.HttpSession;

public class SessionAttribute {
    public static void setSessionUserID(String ID, HttpSession session) {
        session.setAttribute("userID", ID);
    }
    public static String getSessionUserID(HttpSession session) {
        Object retVal = session.getAttribute("userID");
        if(retVal == null) return null;
        return (String) retVal;
    }

    // session Availability checker.
    public static boolean isSessionAvailable(HttpSession session) {
        if (getSessionUserID(session) == null) return false;
        return true;
    }
}
