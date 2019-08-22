package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysApi;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface SysApiMapper {
    int insertOrUpdate(SysApi record);

    int insertOrUpdateSelective(SysApi record);

    List<SysApi> findByAll(SysApi sysApi);

    int batchDel(List<String> idList);
}