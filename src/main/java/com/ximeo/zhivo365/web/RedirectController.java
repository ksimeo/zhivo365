package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ximeo.zhivo365.utilities.SessionUtil.*;

@Controller
public class RedirectController {

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String toMainPage() {
        return "redirect:/start";
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String toMainAdminPage() {
        return "redirect:/admin/start";
    }

    @RequestMapping(value = "/branch1", method = RequestMethod.GET)
    public String showFork1(HttpServletRequest req, Model uiModel, HttpSession session) {
        String res;
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("questForm", qstn);
        if ((res = checkPoint2(req)) != null) return res;
        return "branch1";
    }

    @RequestMapping(value = "/branch2", method = RequestMethod.GET)
    public String showFork2(HttpServletRequest req, HttpSession session, Model uiModel) {
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("questForm", new Question());
        String res;
        if ((res = checkPoint2(req)) != null) return res;
        return "branch2";
    }

    @RequestMapping(value = "/gratitude", method = RequestMethod.GET)
    public String showGratitude(HttpServletRequest req, Model uiModel, HttpSession session) {
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("questForm", new Question());
        String res;
        if ((res = checkPoint2(req)) != null) return res;
        req.getSession().invalidate();
        return "gratitude";
    }

    static String checkPoint2(HttpServletRequest req) {
        if (checkSessionExists(req)) {
            if (isIdentifiedUser(req) || checkUserForm(req)) return "branch1";
        }
        return null;
    }
}