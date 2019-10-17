package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysResource;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;import java.util.Map;

@Mapper
public interface SysResourceMapper {
    int insertOrUpdate(SysResource record);

    int insertOrUpdateSelective(SysResource record);

    List<SysResource> findByAll(SysResource sysResource);

    int batchDel(List<String> idList);

    List<Map<String, Object>> findResource(@Param("type") Integer type, @Param("clientType") Integer clientType, @Param("roleId") String roleId);
}