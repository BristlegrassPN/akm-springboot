package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysDict;

public interface SysDictService {


    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(SysDict record);

    int insertOrUpdateSelective(SysDict record);

}


