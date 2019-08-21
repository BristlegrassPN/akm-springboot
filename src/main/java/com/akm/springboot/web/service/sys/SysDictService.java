package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysDict;

import java.util.List;
import java.util.Map;

public interface SysDictService {


    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(SysDict record);

    int insertOrUpdateSelective(SysDict record);

    List<SysDict> findByAll(SysDict record);

    List<Map<String, Object>> findByType(String type);
}








