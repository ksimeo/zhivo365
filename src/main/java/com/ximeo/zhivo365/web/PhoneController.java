package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Customer;
import com.ximeo.zhivo365.domain.Question;
import com.ximeo.zhivo365.domain.dto.PhoneInfo;
import com.ximeo.zhivo365.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PhoneController {
    private final Logger LOGGER = LoggerFactory.getLogger(PhoneController.class);

    private CustomerService custServ;

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public String enterPhoneNumber(Model uiModel, @Valid PhoneInfo model, HttpServletResponse resp,
                                   HttpSession session) {
        String phoneNumb = model.getPhoneNumb();
        LOGGER.debug("enterPhoneNumber(): User has entered phone number {}.", phoneNumb);
        Cookie cookie = new Cookie("phone", phoneNumb);
        cookie.setMaxAge(60*60*24*30);
        resp.addCookie(cookie);
        Customer cust = custServ.getById(phoneNumb);
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute(qstn);
        if (cust == null) {
            LOGGER.debug("enterPhoneNumber(): A customer with such a phone number is not in the database.");
            return "redirect:/customers?form";
        }
        session.setAttribute("customer", cust);
        LOGGER.debug("enterPhoneNumber(): The visitor has been recognised as a identified customer.");
        uiModel.addAttribute("questForm", new Question());
        return "branch1";
    }

    @Autowired
    public void setCustServ(CustomerService custServ) {
        this.custServ = custServ;
    }
}
