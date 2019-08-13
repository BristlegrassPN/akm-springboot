package com.akm.springboot.web.service.demo;

import com.akm.springboot.web.domain.demo.DemoUser;
import com.akm.springboot.web.mapper.demo.DemoUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoUserServiceImpl implements DemoUserService {

    @Resource
    private DemoUserMapper demoUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return demoUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(DemoUser record) {
        return demoUserMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(DemoUser record) {
        return demoUserMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<DemoUser> findByAll(DemoUser demoUser) {
        return demoUserMapper.findByAll(demoUser);
    }

    @Override
    public String findUsernameById(Integer id) {
        return demoUserMapper.findUsernameById(id);
    }

}





