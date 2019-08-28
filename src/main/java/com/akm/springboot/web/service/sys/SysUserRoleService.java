package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysUserRole;

import java.util.List;
public interface SysUserRoleService{

    int batchInsert(List<SysUserRole> list);

    int updateRoleByUserId(List<String> roleIdList, String userId);

}
