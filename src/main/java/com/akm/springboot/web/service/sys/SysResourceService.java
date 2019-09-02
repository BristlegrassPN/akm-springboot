package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysResource;

import java.util.List;
import java.util.Map;

public interface SysResourceService {


    int insertOrUpdate(SysResource record);

    int insertOrUpdateSelective(SysResource record);

    List<SysResource> findByAll(SysResource sysResource);

    int batchDel(List<String> idList);

    List<Map<String, Object>> findResource(Integer type, Integer clientType);

}



