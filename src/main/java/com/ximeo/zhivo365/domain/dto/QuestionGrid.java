package com.ximeo.zhivo365.domain.dto;

import com.ximeo.zhivo365.domain.Question;

import java.io.Serializable;
import java.util.List;

public class QuestionGrid implements Serializable {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Question> questionData;

    public QuestionGrid() {
        // NOP
    }

    public QuestionGrid(int totalPages, int currentPage, long totalRecords, List<Question> questionData) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.questionData = questionData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Question> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<Question> questionData) {
        this.questionData = questionData;
    }
}
