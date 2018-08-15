package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.UserRepository;
import com.ximeo.zhivo365.domain.User;
import com.ximeo.zhivo365.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("usrServ")
public class UserServiceImpl implements UserService {

    private UserRepository usrRepo;

    @Override
    public void addUser(User usr) {
        usrRepo.save(usr);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return usrRepo.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return Lists.newArrayList(usrRepo.findAll());
    }

    @Override
    public void delUser(long id) {
        usrRepo.delete(id);
    }

    @Autowired
    public void setUsrRepo(UserRepository usrRepo) {
        this.usrRepo = usrRepo;
    }
}