package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.config.RedisKeys;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysRole;
import com.akm.springboot.web.mapper.sys.SysRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int insertOrUpdateSelective(SysRole record) {
        if (StringUtils.isBlank(record.getId())) {
            record.setId(Snowflake.uuid());
        }
        int num = sysRoleMapper.insertOrUpdateSelective(record);
        // 更新角色需要删除缓存数据
        if (StringUtils.isNotBlank(record.getId())) {
            CacheUtils.del(RedisKeys.ROLE_URI.concat(record.getId()));
        }
        return num;
    }

    @Transactional
    @Override
    public int batchDel(List<String> idList) {
        if (idList.isEmpty()) {
            return 0;
        }
        int num = sysRoleMapper.batchDel(idList);
        if (num != 0) {
            // 删除角色需要删除缓存数据
            for (String roleId : idList) {
                CacheUtils.del(RedisKeys.ROLE_URI.concat(roleId));
            }
        }
        return num;
    }

    @Override
    public List<SysRole> findAll(Boolean enable, Byte clientType) {
        SysRole sysRole = new SysRole();
        sysRole.setEnable(enable);
        sysRole.setClientType(clientType);
        return sysRoleMapper.findByAll(sysRole);
    }

    @Override
    public List<String> findLoginUserRoleId(String userId, Byte clientType) {
        return sysRoleMapper.findLoginUserRoleId(userId, clientType);
    }

}
