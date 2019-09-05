package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.config.RedisKeys;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysApi;
import com.akm.springboot.web.mapper.sys.SysApiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysApiServiceImpl implements SysApiService {

    @Resource
    private SysApiMapper sysApiMapper;

    @Override
    public int insertOrUpdate(SysApi record) {
        return sysApiMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SysApi record) {
        if (StringUtils.isBlank(record.getId())) {
            record.setId(Snowflake.uuid());
        }
        return sysApiMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<SysApi> findByAll(SysApi record) {
        return sysApiMapper.findByAll(record);
    }

    @Override
    public int batchDel(List<String> idList) {
        return sysApiMapper.batchDel(idList);
    }

    @Override
    public List<String> getUriByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }
        // 先尝试从缓存获取, 缓存不存在才从数据库获取
        List<String> uriList = CacheUtils.cacheAndGet(RedisKeys.ROLE_URI.concat(roleId), -1, () -> sysApiMapper.getUriByRoleId(roleId));
        return uriList;
    }
}








