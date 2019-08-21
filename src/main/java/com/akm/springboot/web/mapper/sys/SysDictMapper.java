package com.akm.springboot.web.mapper.sys;
import org.apache.ibatis.annotations.Param;

import com.akm.springboot.web.domain.sys.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(SysDict record);

    int insertOrUpdateSelective(SysDict record);

    List<SysDict> findByAll(SysDict sysDict);

    List<Map<String, Object>> findByType(@Param("type")String type);


}