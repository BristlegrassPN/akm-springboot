package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysRole;

import java.util.List;

public interface SysRoleService{

    int deleteByPrimaryKey(String id);

    int insertOrUpdate(SysRole record);

    int insertOrUpdateSelective(SysRole record);

    int batchDel(List<String> idList);

    List<SysRole> findAll(Boolean enable, Byte clientType);
}
