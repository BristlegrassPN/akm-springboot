package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.web.domain.sys.SysRoleResource;
import com.akm.springboot.web.mapper.sys.SysRoleResourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Transactional
    @Override
    public int updateResource(List<String> resourceIdList, String roleId) {
        sysRoleResourceMapper.deleteByRoleId(roleId);
        if (resourceIdList.size() > 0) {
            List<SysRoleResource> list = new ArrayList<>();
            for (String resourceId : resourceIdList) {
                SysRoleResource bean = new SysRoleResource();
                bean.setId(Snowflake.uuid());
                bean.setRoleId(roleId);
                bean.setResourceId(resourceId);
                list.add(bean);
            }
            return sysRoleResourceMapper.batchInsert(list);
        }
        return 0;
    }
}

