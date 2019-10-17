package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insertOrUpdateSelective(SysRole record);

    List<SysRole> findByAll(SysRole sysRole);

    int batchDel(List<String> idList);

    List<Map<String, String>> findRoleByUser(@Param("userId") String userId, @Param("clientType") Byte clientType);

}