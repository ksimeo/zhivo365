package com.ximeo.zhivo365.services;

import com.ximeo.zhivo365.domain.Irrigation;

import java.util.List;

public interface IrrigationService {

    List<Irrigation> getAll();
    Irrigation getById(long id);
    void addOne(Irrigation irr);
    void delOne(Long id);
}
