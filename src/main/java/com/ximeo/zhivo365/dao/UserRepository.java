package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
