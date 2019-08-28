package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysResourceApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysResourceApiMapper {
    int batchInsert(@Param("list") List<SysResourceApi> list);

    int deleteByResourceId(String resourceId);
}