package com.akm.springboot.web.service.demo;

import com.akm.springboot.web.domain.demo.DemoUser;

import java.util.List;

public interface DemoUserService {


    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(DemoUser record);

    int insertOrUpdateSelective(DemoUser record);

    List<DemoUser> findByAll(DemoUser demoUser);

    String findUsernameById(Integer id);
}


