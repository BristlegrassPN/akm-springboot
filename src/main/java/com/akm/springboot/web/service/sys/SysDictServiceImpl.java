package com.akm.springboot.web.service.sys;

import com.akm.springboot.web.domain.sys.SysDict;
import com.akm.springboot.web.mapper.sys.SysDictMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(SysDict record) {
        return sysDictMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SysDict record) {
        return sysDictMapper.insertOrUpdateSelective(record);
    }

}


