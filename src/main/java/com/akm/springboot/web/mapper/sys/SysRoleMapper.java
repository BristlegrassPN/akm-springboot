package com.akm.springboot.web.mapper.sys;
import com.akm.springboot.web.domain.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insertOrUpdate(SysRole record);

    int insertOrUpdateSelective(SysRole record);

    List<SysRole> findByAll(SysRole sysRole);

    int batchDel(List<String> idList);

}