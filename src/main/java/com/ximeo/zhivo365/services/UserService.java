package com.ximeo.zhivo365.services;

import com.ximeo.zhivo365.domain.User;

import java.util.List;

public interface UserService {

    void addUser(User usr);
    User getUser(long id);
    List<User> getAllUsers();
    void delUser(long id);
}
