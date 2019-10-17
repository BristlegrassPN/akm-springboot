package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysUserDetail;
import com.akm.springboot.web.domain.sys.SysUserEntity;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    String insertSelective(SysUserEntity record);

    String updateByPrimaryKeySelective(SysUserEntity record);

    int updatePassword(String id, String oldPassword, String newPassword);

    int updatePassword(String id, String newPassword);

    int deleteByPrimaryKey(String id);

    Map<String, Object> login(String username, String password, Byte clientType);

    List<SysUserDetail> findByAll(SysUserDetail detail);
}







