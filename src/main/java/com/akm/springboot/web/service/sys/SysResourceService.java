package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysResource;

import java.util.List;

public interface SysResourceService {


    int insertOrUpdate(SysResource record);

    int insertOrUpdateSelective(SysResource record);

    List<SysResource> findByAll(SysResource sysResource);

    int updateDelById(List<String> ids);
}



