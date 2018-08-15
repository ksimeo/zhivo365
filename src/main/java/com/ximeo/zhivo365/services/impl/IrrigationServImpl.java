package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.IrrigationRepository;
import com.ximeo.zhivo365.domain.Irrigation;
import com.ximeo.zhivo365.services.IrrigationService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
@Service("irrServ")
public class IrrigationServImpl implements IrrigationService {
    private IrrigationRepository irrRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Irrigation> getAll() {
        return Lists.newArrayList(irrRepo.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Irrigation getById(long id) {
        return irrRepo.findOne(id);
    }

    @Override
    public void addOne(Irrigation irr) {
        irrRepo.save(irr);
    }

    @Override
    public void delOne(Long id) {
        irrRepo.delete(id);
    }

    @Resource
    public void setIrrRepo(IrrigationRepository irrRepo) {
        this.irrRepo = irrRepo;
    }
}
