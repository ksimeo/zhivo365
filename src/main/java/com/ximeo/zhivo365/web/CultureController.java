package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Culture;
import com.ximeo.zhivo365.domain.dto.Message;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.services.CultureService;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Controller
public class CultureController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CultureController.class);

    private CultureService cultServ;
    private QuestionService questServ;
    private OrderService ordServ;
    private MessageSource messageSource;

    @RequestMapping(value = "/admin/cultures", method = RequestMethod.GET)
    public String showCulturesList(Map<String, Object> uiModel) {
        LOGGER.debug("showCulturesList()");
        uiModel.put("cults", cultServ.getCultures());
        uiModel.put("cultForm", new Culture());
        uiModel.put("count", questServ.getUnreadedQuestionCount());
        uiModel.put("count1", ordServ.getUnreadedOrderCount());
        uiModel.put("passwForm", new PasswInfo());
        return "cultures";
    }

    @RequestMapping(value = "/admin/cultures", method = RequestMethod.POST)
    public String saveCulture(Culture cult, RedirectAttributes redAttr, Locale locale) {
        LOGGER.info("saveCulture()");
        try {
            cultServ.addCulture(cult);
            if (cult.isNew()) {
                redAttr.addAttribute("msg", new Message("success",
                        messageSource.getMessage("culture_added", new Object[]{}, locale)));
            }
            else {
                redAttr.addAttribute("msg", new Message("success", messageSource.getMessage(
                        "culture_changed", new Object[]{}, locale)));
            }
        } catch (Exception e) {
            redAttr.addAttribute("msg", new Message("danger", messageSource.getMessage("system_error",
                    new Object[]{}, locale)));
            LOGGER.error("saveCulture()");
            return "cultures";
        }
        return "redirect:../admin/cultures";
    }

    @RequestMapping(value = "/admin/culture/{id}/delete", method = RequestMethod.GET)
    public String deleteCulture(@PathVariable("id") long id, RedirectAttributes redAttr, Locale locale) {
        LOGGER.info("deleteCulture()");
        try {
            cultServ.delCulture(id);
            redAttr.addFlashAttribute("msg", new Message("success", messageSource.getMessage(
                    "culture_deleted", new Object[]{}, locale)));
        } catch (Exception e) {
            LOGGER.error("deleteCulture()");
            redAttr.addFlashAttribute("msg", new Message("danger", messageSource.getMessage(
                    "system_error", new Object[]{}, locale)));
            return "cultures";
        }
        return "redirect:../admin/cultures";
    }

    @RequestMapping(value = "/admins/cultures/{id}")
    public String showCulture(@PathVariable("id") long id, HashMap<String, Object> uiModel) {
        LOGGER.info("showCulture()");
        uiModel.put("cultures", cultServ.getCulture(id));
        return "culture";
    }

    @Autowired
    public void setCultServ(CultureService cultServ) {
        this.cultServ = cultServ;
    }

    @Autowired
    public void setQuestServ(QuestionService questServ) {
        this.questServ = questServ;
    }

    @Autowired
    public void setOrdServ(OrderService ordServ) {
        this.ordServ = ordServ;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}