package com.ximeo.zhivo365.services;

import com.ximeo.zhivo365.domain.Culture;

import java.util.List;

public interface CultureService {

    void addCulture(Culture culture);
    Culture getCulture(long id);
    List<Culture> getCultures();
    void delCulture(long id);
}
