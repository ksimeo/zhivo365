package com.ximeo.zhivo365.web;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.domain.Customer;
import com.ximeo.zhivo365.domain.Order;
import com.ximeo.zhivo365.domain.Product;
import com.ximeo.zhivo365.domain.Question;
import com.ximeo.zhivo365.domain.dto.OrderGrid;
import com.ximeo.zhivo365.domain.dto.OrderInfo;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private ProductService prodServ;
    private OrderService ordServ;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String showOrderForm(Model uiModel, @Param("amount") int amount, @Param("type") int type,
                                HttpSession session) {
        List<Product> prods = prodServ.getAll();
        uiModel.addAttribute(prods);
        OrderInfo ordInf = new OrderInfo();
        if (amount != 0 && type != 0) {
            ordInf.setAmount(amount);
            ordInf.setType(type);
            uiModel.addAttribute("prodType", type);
        }
        Question qstn = (Question) session.getAttribute("qstn");
        uiModel.addAttribute("orderForm", ordInf);
        uiModel.addAttribute("questForm", qstn);
        return "orderform";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String doOrdering(OrderInfo model, HttpSession session) {
        Customer cust = (Customer) session.getAttribute("customer");
        if (cust == null) {
            return "redirect:/start";
        }
        Product prod = prodServ.getById(model.getType());
        Order ord = new Order();
        ord.setCustomer(cust);
        ord.setProduct(prod);
        ord.setAmount(model.getAmount());
        ordServ.addOrder(ord);
        return "redirect:/branch2";
    }

    @RequestMapping(value = "admin/orders/listgrid", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public OrderGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "rows", required = false) Integer rows,
                              @RequestParam(value = "sidx", required = false) String sortBy,
                              @RequestParam(value = "sord", required = false) String order) {
        LOGGER.info("Listing orders for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing orders for grid with sort: {}, order: {}", sortBy, order);
        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equalsIgnoreCase("createDate"))
            orderBy = "createDate";
        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<Order> ordersPage = ordServ.getOrderPage(pageRequest);
        List<Order> ordrs2 = Lists.newArrayList(ordersPage.iterator());
        for (Order ord : ordrs2) {
            System.out.println(ord);
        }
        OrderGrid ordGrid1 = new OrderGrid();
        ordGrid1.setCurrentPage(ordersPage.getNumber() + 1);
        ordGrid1.setTotalPages(ordersPage.getTotalPages());
        ordGrid1.setTotalRecords(ordersPage.getTotalElements());
        ordGrid1.setOrderData(ordrs2);
        return ordGrid1;
    }

    @Autowired
    public void setProdServ(ProductService prodServ) {
        this.prodServ = prodServ;
    }

    @Autowired
    public void setOrdServ(OrderService ordServ) {
        this.ordServ = ordServ;
    }
}
