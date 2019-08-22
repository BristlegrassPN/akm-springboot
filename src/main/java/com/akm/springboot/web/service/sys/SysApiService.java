package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysApi;

import java.util.List;

public interface SysApiService {

    int insertOrUpdate(SysApi record);

    int insertOrUpdateSelective(SysApi record);

    List<SysApi> findByAll(SysApi record);

    int batchDel(List<String> idList);
}








