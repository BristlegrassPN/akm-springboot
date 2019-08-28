package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    int batchInsert(@Param("list") List<SysUserRole> list);

    int deleteByUserId(String userId);
}