package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Irrigation;
import com.ximeo.zhivo365.domain.dto.Message;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.services.IrrigationService;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class IrrigationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IrrigationController.class);

    private IrrigationService irrServ;
    private QuestionService questServ;
    private OrderService ordServ;
    private MessageSource messageSource;

    @RequestMapping(value="/admin/irrs", method = RequestMethod.GET)
    public String showIrrigations(Model uiModel) {
        LOGGER.info("showIrrigation()");
        uiModel.addAttribute("irrs", irrServ.getAll());
        uiModel.addAttribute("irrForm", new Irrigation());
        uiModel.addAttribute("count", questServ.getUnreadedQuestionCount());
        uiModel.addAttribute("count1", ordServ.getUnreadedOrderCount());
        uiModel.addAttribute("passwForm", new PasswInfo());
        return "irrigations";
    }

    @RequestMapping(value = "/admin/irrs", method = RequestMethod.POST)
    public String saveIrrigation(Irrigation model, RedirectAttributes redAttr, Locale locale) {
        LOGGER.info("saveIrrigation(): {}", model);
        try {
            irrServ.addOne(model);
            if (model.isNew()) {
                redAttr.addFlashAttribute("msg", new Message("success",
                        messageSource.getMessage("new_irrigation_added", new Object[]{}, locale)));
            } else {
                redAttr.addFlashAttribute("msg", new Message("success",
                        messageSource.getMessage("irrigation_changed", new Object[]{}, locale)));
            }
        } catch (Exception e) {
            LOGGER.error("saveIrrigation()");
            redAttr.addFlashAttribute("msg", new Message("danger", messageSource.getMessage("system_error",
                    new Object[]{}, locale)));
            return "irrigations";
        }
        return "redirect:../admin/irrs";
    }

    @RequestMapping(value = "/admins/irrs/{id}/delete", method = RequestMethod.GET)
    private String deleteIrrigation(@PathVariable("id")long id, RedirectAttributes redAttr, Locale locale) {
        LOGGER.info("deleteIrrigation()");
        try {
            irrServ.delOne(id);
            redAttr.addFlashAttribute("msg", new Message("success",
                    messageSource.getMessage("irrigation_deleted", new Object[]{}, locale)));
        } catch (Exception e) {
            LOGGER.error("deleteIrrigation()");
            redAttr.addFlashAttribute("msg", new Message("danger", messageSource.getMessage("system_error",
                    new Object[]{}, locale)));
            return "irrigations";
        }
        return "redirect:../admin/irrs";
    }

    @Autowired
    public void setIrrServ(IrrigationService irrServ) {
        this.irrServ = irrServ;
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