package com.akm.springboot.web.mapper.demo;
import com.akm.springboot.web.domain.demo.DemoUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(DemoUser record);

    int insertOrUpdateSelective(DemoUser record);

    List<DemoUser> findByAll(DemoUser demoUser);

    String findUsernameById(Integer id);

}