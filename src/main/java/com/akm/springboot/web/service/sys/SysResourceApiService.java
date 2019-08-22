package com.akm.springboot.web.service.sys;

import java.util.List;

public interface SysResourceApiService {
    int updateApi(List<String> apiIdList, String resourceId);
}


