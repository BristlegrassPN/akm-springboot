package com.akm.springboot.web.service.sys;

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
    public int updateDelById(List<String> idList) {
        return sysApiMapper.updateDelById(idList);
    }
}








