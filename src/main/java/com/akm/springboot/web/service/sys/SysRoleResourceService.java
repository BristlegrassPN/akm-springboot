package com.akm.springboot.web.service.sys;

import java.util.List;

public interface SysRoleResourceService {

    int updateResourceByRoleId(List<String> resourceIdList, String roleId);

}

