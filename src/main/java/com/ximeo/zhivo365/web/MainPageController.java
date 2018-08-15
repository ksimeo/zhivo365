package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Question;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.domain.dto.PhoneInfo;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);

    private QuestionService questServ;
    private OrderService ordServ;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String showMainPage(Model uiModel, HttpServletRequest req,
                               @CookieValue(name = "phone", required = false) Cookie phoneCookie, HttpSession session) {
        LOGGER.info("showMainPage(). Customer with IP {} is logged on the main public page.", req.getRemoteAddr());
        Question qstn = new Question();
        session.setAttribute("qstn", qstn);
        uiModel.addAttribute("questForm", qstn);
        PhoneInfo phInf = new PhoneInfo();
        if (phoneCookie != null) phInf.setPhoneNumb(phoneCookie.getValue());
        uiModel.addAttribute("phoneForm", phInf);
        return "customers/start";
    }

    @RequestMapping(value = "/admin/start", method = RequestMethod.GET)
    public String showMainAdminPage(HttpServletRequest req, Model uiModel) {
        LOGGER.info("showMainAdminPage(). Customer with IP {} is logged on the main admin page.", req.getRemoteAddr());
        uiModel.addAttribute("count", questServ.getUnreadedQuestionCount());
        uiModel.addAttribute("count1", ordServ.getUnreadedOrderCount());
        uiModel.addAttribute("passwForm", new PasswInfo());
        return "admin/main";
    }

    @Autowired
    public void setQuestServ(QuestionService questServ) {
        this.questServ = questServ;
    }

    @Autowired
    public void setOrdServ(OrderService ordServ) {
        this.ordServ = ordServ;
    }
}
