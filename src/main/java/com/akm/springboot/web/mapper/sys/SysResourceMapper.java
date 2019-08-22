package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysResource;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface SysResourceMapper {
    int insertOrUpdate(SysResource record);

    int insertOrUpdateSelective(SysResource record);

    List<SysResource> findByAll(SysResource sysResource);

    int batchDel(List<String> idList);
}