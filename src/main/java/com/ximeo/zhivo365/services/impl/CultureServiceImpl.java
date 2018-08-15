package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.CulturesRepository;
import com.ximeo.zhivo365.domain.Culture;
import com.ximeo.zhivo365.services.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service
public class CultureServiceImpl implements CultureService {
    private CulturesRepository culturesRepository;

    @Override
    public void addCulture(Culture culture) {
        culturesRepository.save(culture);
    }

    @Transactional(readOnly = true)
    @Override
    public Culture getCulture(long id) {
        return culturesRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Culture> getCultures() {
        return Lists.newArrayList(culturesRepository.findAll());
    }

    @Override
    public void delCulture(long id) {
        culturesRepository.delete(id);
    }

    @Autowired
    public void setCulturesRepository(CulturesRepository culturesRepository) {
        this.culturesRepository = culturesRepository;
    }
}