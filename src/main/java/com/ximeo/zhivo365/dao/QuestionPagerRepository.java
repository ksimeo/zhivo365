package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionPagerRepository extends PagingAndSortingRepository<Question, Long> {
}
