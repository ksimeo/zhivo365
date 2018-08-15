package com.ximeo.zhivo365.web;

import com.ximeo.zhivo365.domain.Culture;
import com.ximeo.zhivo365.domain.Question;
import com.ximeo.zhivo365.services.CultureService;
import com.ximeo.zhivo365.services.IrrigationService;
import com.ximeo.zhivo365.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationController.class);

    private ProductService prodServ;
    private CultureService cultServ;
    private IrrigationService irrServ;

    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    public String showCalcPage(HttpServletRequest req, HttpSession session, Model uiModel) {
        String res;
        if ((res = RedirectController.checkPoint2(req)) != null) return res;
        uiModel.addAttribute("prods", prodServ.getAll());
        uiModel.addAttribute("irrigations", irrServ.getAll());
        List<Culture> cults = cultServ.getCultures();
        int cultSize1 = cults.size()/2;
        int cultSize2 = cults.size()/2 - cultSize1;
        List<Culture> cults1 = new ArrayList<>(cultSize1);
        List<Culture> cults2 = new ArrayList<>(cultSize2);
        for (int i = 0; i < cults.size(); i++) {
            if (i < cultSize1) cults1.add(cults.get(i));
            else cults2.add(cults.get(i));
        }
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("cultures1", cults1);
        uiModel.addAttribute("cultures2", cults2);
        uiModel.addAttribute("questForm", qstn);
        return "calc";
    }

    @Autowired
    public void setProdServ(ProductService prodServ) {
        this.prodServ = prodServ;
    }

    @Autowired
    public void setIrrServ(IrrigationService irrServ) {
        this.irrServ = irrServ;
    }

    @Autowired
    public void setCultServ(CultureService cultServ) {
        this.cultServ = cultServ;
    }
}
