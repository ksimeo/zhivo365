package com.ximeo.zhivo365.domain.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PhoneInfo implements Serializable {
    @Size(min = 10, max = 17)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    String phoneNumb;

    public PhoneInfo() {
        //NOP
    }

    public PhoneInfo(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    @Override
    public String toString() {
        return phoneNumb;
    }
}