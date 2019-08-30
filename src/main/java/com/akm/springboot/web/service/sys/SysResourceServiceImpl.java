package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysResource;
import com.akm.springboot.web.mapper.sys.SysResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public int insertOrUpdate(SysResource record) {
        return sysResourceMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SysResource record) {
        if (StringUtils.isBlank(record.getId())) {
            record.setId(Snowflake.uuid());
        }
        return sysResourceMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<SysResource> findByAll(SysResource sysResource) {
        return sysResourceMapper.findByAll(sysResource);
    }

    @Override
    public int batchDel(List<String> idList) {
        return sysResourceMapper.batchDel(idList);
    }

    @Override
    public List<Map<String, String>> findResource(Integer type, Integer clientType) {
        return sysResourceMapper.findResource(type, clientType);
    }
}



