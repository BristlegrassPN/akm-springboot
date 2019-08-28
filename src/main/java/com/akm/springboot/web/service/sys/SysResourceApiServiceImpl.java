package com.akm.springboot.web.service.sys;

import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.web.domain.sys.SysResourceApi;
import com.akm.springboot.web.mapper.sys.SysResourceApiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysResourceApiServiceImpl implements SysResourceApiService {

    @Resource
    private SysResourceApiMapper sysResourceApiMapper;

    @Transactional
    @Override
    public int updateApiByResourceId(List<String> apiIdList, String resourceId) {
        sysResourceApiMapper.deleteByResourceId(resourceId);
        if (apiIdList.size() > 0) {
            List<SysResourceApi> list = new ArrayList<>();
            for (String apiId : apiIdList) {
                SysResourceApi bean = new SysResourceApi();
                bean.setId(Snowflake.uuid());
                bean.setResourceId(resourceId);
                bean.setApiId(apiId);
                list.add(bean);
            }
            return sysResourceApiMapper.batchInsert(list);
        }
        return 0;
    }
}


