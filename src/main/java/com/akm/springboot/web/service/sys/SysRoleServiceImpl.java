package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysRole;
import com.akm.springboot.web.mapper.sys.SysRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(SysRole record) {
        return sysRoleMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SysRole record) {
        if (StringUtils.isBlank(record.getId())) {
            record.setId(Snowflake.uuid());
        }
        return sysRoleMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<SysRole> find(Boolean enable, Byte clientType) {
        SysRole sysRole = new SysRole();
        sysRole.setEnable(enable);
        sysRole.setClientType(clientType);
        return sysRoleMapper.findByAll(sysRole);
    }

    @Override
    public int batchDel(List<String> idList) {
        return sysRoleMapper.batchDel(idList);
    }

}
