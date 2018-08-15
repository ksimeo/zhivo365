package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.User;
import com.ximeo.zhivo365.domain.UserRole;
import com.ximeo.zhivo365.domain.dto.Message;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.QuestionService;
import com.ximeo.zhivo365.services.UserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService usrServ;
    private OrderService ordServ;
    private QuestionService questServ;
    private MessageSource messageSource;

    @RequestMapping(value="/admin/users", method = RequestMethod.GET)
    public String showUsersList(Model uiModel) {
        LOGGER.info("showUsersList()");
        uiModel.addAttribute("users", usrServ.getAllUsers());
        uiModel.addAttribute("usrForm", new User());
        uiModel.addAttribute("roles", UserRole.values());
        uiModel.addAttribute("count", questServ.getUnreadedQuestionCount());
        uiModel.addAttribute("count1", ordServ.getUnreadedOrderCount());
        uiModel.addAttribute("passwForm", new PasswInfo());
        return "users";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public String saveUser(User usr, @RequestParam(value = "file", required = false) Part file,
                           RedirectAttributes redAttr, Locale locale) {
        if (file != null) {
            LOGGER.info("File name: " + file.getName());
            LOGGER.info("File size: " + file.getSize());
            LOGGER.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) LOGGER.info("File input stream is null");
                fileContent = IOUtils.toByteArray(inputStream);
                usr.setImage(fileContent);
                redAttr.addFlashAttribute("msg",
                        new Message("success", messageSource.getMessage("user_saved", new Object[]{}, locale)));
            } catch (IOException ex) {
                LOGGER.error("Error saving uploaded file");
                redAttr.addFlashAttribute("msg",
                        new Message("danger", messageSource.getMessage("system_error", new Object[]{}, locale)));
            }
        }
        usrServ.addUser(usr);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admins/users/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        usrServ.delUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admins/users/{id}/pswrd", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") long id,Model uiModel) {
        PasswInfo model = new PasswInfo();
        uiModel.addAttribute("passwForm", model);
        return "admin/chpswrd";
    }

    @RequestMapping(value = "/admins/users", method = RequestMethod.POST, params = "chpswrd")
    public String saveNewPassword(PasswInfo model, HttpSession session) {
        User usr = (User)session.getAttribute("user");
        if ((usr.getPassword()).equals(model.getOldPassword())) {
            usr.setPassword(model.getNewPassword());
            usrServ.addUser(usr);
            return "redirect:/admin";
        }
        return "private/chpswrd";
    }

    @RequestMapping(value = "/admin/users/{id}/image", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadImage(@PathVariable("id") Long id) {
        User usr = usrServ.getUser(id);
        if (usr.getImage() != null) {
            LOGGER.info("Downloading photo for id: {} with size: {}", usr.getId(), usr.getImage());
        }
        return usr.getImage();
    }

    @Autowired
    public void setUsrServ(UserService usrServ) {
        this.usrServ = usrServ;
    }

    @Autowired
    public void setOrdServ(OrderService ordServ) {
        this.ordServ = ordServ;
    }

    @Autowired
    public void setQuestServ(QuestionService questServ) {
        this.questServ = questServ;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}