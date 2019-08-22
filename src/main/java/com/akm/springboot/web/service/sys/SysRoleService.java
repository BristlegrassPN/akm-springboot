package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysRole;

import java.util.List;

public interface SysRoleService{

    int deleteByPrimaryKey(String id);

    int insertOrUpdate(SysRole record);

    int insertOrUpdateSelective(SysRole record);

    List<SysRole> findByAll(SysRole sysRole);

    int batchDel(List<String> idList);
}
