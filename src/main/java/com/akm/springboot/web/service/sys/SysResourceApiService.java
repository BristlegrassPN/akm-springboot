package com.akm.springboot.web.service.sys;

import java.util.List;

public interface SysResourceApiService {
    int updateApiByResourceId(List<String> apiIdList, String resourceId);
}


