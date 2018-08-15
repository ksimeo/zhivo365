package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Customer;
import com.ximeo.zhivo365.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ximeo.zhivo365.utilities.SessionUtil.*;

@Controller
public class CustomerController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/customers", method = RequestMethod.GET, params = "form")
    public String showCustomerForm(Model uiModel, HttpSession session) {
        Customer cust = new Customer();
        uiModel.addAttribute("usrForm", cust);
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("questForm", qstn);
        return "regform";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String enterUserForm(Customer cust, HttpServletRequest req, HttpSession session) {
        String res;
        if ((res = checkPoint1(req)) != null) return res;
        LOGGER.debug("User has entered name: {}, surname: {}, email: {}, region: {}", cust.getName(), cust.getSurname(),
                cust.getEmail(), cust.getRegionName());
        session.setAttribute("customer", cust);
        return "redirect:/branch1";
    }

    private static String checkPoint1(HttpServletRequest req) {
        if (checkSessionExists(req)) {
            if (isIdentifiedUser(req)) return "/";
            if (checkPhoneEntered(req)) return null;
        }
        return null;
    }
}