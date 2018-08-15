package com.ximeo.zhivo365.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static boolean checkSessionExists(HttpServletRequest req) {
        return req.getSession(false) != null;
    }

    public static boolean checkPhoneEntered(HttpServletRequest req) {
        if (!checkSessionExists(req)) return false;
        HttpSession session = req.getSession();
        return req.getSession().getAttribute("phone") != null;
    }

    public static boolean checkUserForm(HttpServletRequest req) {
        if (!checkSessionExists(req)) return false;
        if (!checkPhoneEntered(req)) return false;
        return req.getSession().getAttribute("user") != null;
    }

    public static boolean isIdentifiedUser(HttpServletRequest req) {
        if (!checkSessionExists(req)) return false;
        return req.getSession().getAttribute("customer") != null;
    }
}
