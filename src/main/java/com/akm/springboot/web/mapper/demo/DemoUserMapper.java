package com.akm.springboot.web.mapper.demo;

import com.akm.springboot.web.domain.demo.DemoUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(DemoUser record);

    int insertOrUpdateSelective(DemoUser record);

    List<DemoUser> findByAll(DemoUser demoUser);

    String findUsernameById(Integer id);

    List<Map<String,Object>> findList(Map<String,Object> param);

    int insertOrUpdateSelective(Map<String,Object> param);

}