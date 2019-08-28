package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleResourceMapper {
    int batchInsert(@Param("list") List<SysRoleResource> list);

    int deleteByRoleId(String roleId);
}