package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(SysDict record);

    int insertOrUpdateSelective(SysDict record);

    List<SysDict> findByAll(SysDict sysDict);
}