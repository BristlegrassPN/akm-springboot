package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.web.domain.sys.SysUserRole;
import com.akm.springboot.web.mapper.sys.SysUserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int batchInsert(List<SysUserRole> list) {
        return sysUserRoleMapper.batchInsert(list);
    }

    @Override
    public int updateRoleByUserId(List<String> roleIdList, String userId) {
        sysUserRoleMapper.deleteByUserId(userId);
        if (roleIdList.size() > 0) {
            List<SysUserRole> list = new ArrayList<>();
            for (String roleId : roleIdList) {
                SysUserRole bean = new SysUserRole();
                bean.setId(Snowflake.uuid());
                bean.setUserId(userId);
                bean.setRoleId(roleId);
                list.add(bean);
            }
            return sysUserRoleMapper.batchInsert(list);
        }
        return 0;
    }

}
