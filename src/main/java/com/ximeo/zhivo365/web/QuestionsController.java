package com.ximeo.zhivo365.web;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.domain.dto.PasswInfo;
import com.ximeo.zhivo365.domain.dto.QuestionGrid;
import com.ximeo.zhivo365.domain.Question;
import com.ximeo.zhivo365.services.OrderService;
import com.ximeo.zhivo365.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionsController.class);

    private QuestionService questServ;
    private OrderService ordServ;

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public String saveQuestionForm(Question qstn) {
        LOGGER.debug("getQuestionForm(): {}", qstn);
        questServ.addQuestion(qstn);
        return "gratitude2";
    }

    @RequestMapping(value = "/admin/quests", method = RequestMethod.GET)
    public String getQuestionsList(Model uiModel) {
        LOGGER.info("getQuestionsList()");
        uiModel.addAttribute("count", questServ.getUnreadedQuestionCount());
        uiModel.addAttribute("count1", ordServ.getUnreadedOrderCount());
        uiModel.addAttribute("passwForm", new PasswInfo());
        return "questions";
    }

    @RequestMapping(value = "/admin/question/{id}", method = RequestMethod.GET)
    public String showQuestion(@PathVariable("id") long id, Model uiModel) {
        LOGGER.info("showQuestion()");
        uiModel.addAttribute("question", questServ.getQuestion(id));
        return "question";
    }

    @RequestMapping(value = "/admins/question/{id}/view", method = RequestMethod.GET)
    public void setViewed(@PathVariable("id") long id) {
        LOGGER.info("setViewed()");
        Question qstn = questServ.getQuestion(id);
        questServ.addQuestion(qstn);
    }

    @RequestMapping(value = "admins/questions/listgrid", method = RequestMethod.GET,
            produces="application/json;charset=UTF-8")
    @ResponseBody
    public QuestionGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "rows", required = false) Integer rows,
                              @RequestParam(value = "sidx", required = false) String sortBy,
                              @RequestParam(value = "sord", required = false) String order) {

        LOGGER.info("Listing contacts for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing contacts for grid with sort: {}, order: {}", sortBy, order);

        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("customerName"))
            orderBy = "customerName";

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

        Page<Question> questionPage = questServ.getQuestionPage(pageRequest);

        // Construct the grid data that will return as JSON data
        QuestionGrid questionGrid = new QuestionGrid();

        questionGrid.setCurrentPage(questionPage.getNumber() + 1);
        questionGrid.setTotalPages(questionPage.getTotalPages());
        questionGrid.setTotalRecords(questionPage.getTotalElements());
        questionGrid.setQuestionData(Lists.newArrayList(questionPage.iterator()));

//        questionGrid.setQuestionData(Lists.newArrayList(questionPage));
        return questionGrid;
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
