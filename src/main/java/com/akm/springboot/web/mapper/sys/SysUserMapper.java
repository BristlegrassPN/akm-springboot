package com.akm.springboot.web.mapper.sys;

import com.akm.springboot.web.domain.sys.SysUserDetail;
import com.akm.springboot.web.domain.sys.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int insertSelective(SysUserEntity record);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updatePassword(@Param("id") String id, @Param("password") String password, @Param("salt") String salt);

    int deleteByPrimaryKey(String id);

    SysUserEntity selectOneByIdOrUsername(@Param("id") String id, @Param("username") String username);

    List<SysUserDetail> findByAll(SysUserDetail detail);

}