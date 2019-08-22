package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysResource;
import com.akm.springboot.web.mapper.sys.SysResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<SysResource> all = sysResourceMapper.findByAll(sysResource);
        return all;
    }

    @Override
    public int updateDelById(List<String> idList) {
        return sysResourceMapper.updateDelById(idList);
    }
}



