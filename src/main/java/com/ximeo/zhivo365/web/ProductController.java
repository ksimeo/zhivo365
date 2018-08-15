package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.CurrencyType;
import com.ximeo.zhivo365.domain.MeasureUnit;
import com.ximeo.zhivo365.domain.Product;
import com.ximeo.zhivo365.domain.dto.Message;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.ProductService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private QuestionService questServ;
    private OrderService ordServ;
    private ProductService prodServ;
    private MessageSource messageSource;

    @RequestMapping(value = "/admin/prods", method = RequestMethod.GET)
    public String showProductsList(Model uiModel) {
        LOGGER.info("showProductsPage()");
        List<Product> prods = prodServ.getAll();
        uiModel.addAttribute("products", prods);
        uiModel.addAttribute("units", MeasureUnit.values());
        uiModel.addAttribute("currencies", CurrencyType.values());
        uiModel.addAttribute("prodForm", new Product());
        uiModel.addAttribute("count", questServ.getUnreadedQuestionCount());
        uiModel.addAttribute("count1", ordServ.getUnreadedOrderCount());
        uiModel.addAttribute("passwForm", new PasswInfo());
        return "products";
    }

    @RequestMapping(value = "/admins/prods", method = RequestMethod.GET, params = "form")
    public String showNewProductForm(Model uiModel) {
        Product prod = new Product();
        uiModel.addAttribute("currencies", CurrencyType.values());
        uiModel.addAttribute("units", MeasureUnit.values());
        uiModel.addAttribute("prodForm", prod);
        return "admins/prdfrm";
    }

    @RequestMapping(value = "/admins/prods/{id}", method = RequestMethod.GET)
    public String showEditProductForm(@PathVariable("id") int prodId, Model uiModel) {
        Product prod = prodServ.getById(prodId);
        uiModel.addAttribute("currencies", CurrencyType.values());
        uiModel.addAttribute("units", MeasureUnit.values());
        uiModel.addAttribute("prodForm", prod);
        return "admins/prdfrm";
    }

    @RequestMapping(value = "/admin/prods", method = RequestMethod.POST)
    public String saveProduct(Product model, Model uiModel, HttpServletResponse resp, RedirectAttributes redAttr,
                              Locale locale) {
        LOGGER.info("saveProduct(): {}", model);
        try {
            prodServ.addProduct(model);
            if (model.isNew()) {
                redAttr.addFlashAttribute("msg", new Message("success",
                        messageSource.getMessage("product_added", new Object[]{}, locale)));
            } else {
                redAttr.addFlashAttribute("msg", new Message("success",
                        messageSource.getMessage("product_changed", new Object[]{}, locale)));;
            }
        } catch (Exception e) {
            LOGGER.error("saveProduct()");
            redAttr.addFlashAttribute("msg", new Message("danger", messageSource.getMessage("system_error",
                    new Object[]{}, locale)));
            return "products";
        }
            return "redirect:/admin/prods";
    }

    @RequestMapping(value = "/admin/prods/{id}/delete", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id")long id, HttpServletResponse resp, RedirectAttributes redAttr,
                                Locale locale) {
        LOGGER.info("deleteProduct()");
        try {
            prodServ.delProduct(id);
            redAttr.addFlashAttribute("msg", new Message("success",
                    messageSource.getMessage("product_deleted", new Object[]{}, locale)));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("deleteProduct()");
            redAttr.addFlashAttribute("msg", new Message("danger", messageSource.getMessage("system_error",
                    new Object[]{}, locale)));
            return "products";
        }
            return "forward:/admin/prods";
    }

    @Autowired
    public void setProdServ(ProductService prodServ) {
        this.prodServ = prodServ;
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