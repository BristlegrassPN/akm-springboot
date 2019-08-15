package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysDict;

import java.util.List;

public interface SysDictService {


    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(SysDict record);

    int insertOrUpdateSelective(SysDict record);

    List<SysDict> findByAll(SysDict record);
}








