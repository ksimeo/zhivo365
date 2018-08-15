package com.ximeo.zhivo365.domain;

public enum UserRole {
    GUEST("Гость"), USER("Пользователь"), ADMIN("Администратор");

    String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}