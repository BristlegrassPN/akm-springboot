package com.akm.springboot.web.service.sys;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.web.domain.sys.SysUserDetail;
import com.akm.springboot.web.domain.sys.SysUserEntity;
import com.akm.springboot.web.mapper.sys.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUserEntity login(String username, String password, String clientType) {
        SysUserEntity user = sysUserMapper.selectOneByIdOrUsername(null, username);
        AssertUtils.isTrue(user.getEnable(), "账号已被禁用");
        AssertUtils.isTrue(!user.getDel(), "账号已失效");
        if (user.getExpiredTime() != null) {
            AssertUtils.isTrue(new Date().compareTo(user.getExpiredTime()) < 0, "账号已过期");
        }
        String p = SecureUtil.md5(SecureUtil.sha256(password) + user.getSalt() + username);
        AssertUtils.isTrue(p.equals(user.getPassword()), "密码错误");
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    @Override
    public int updatePassword(String id, String oldPassword, String newPassword) {
        SysUserEntity user = sysUserMapper.selectOneByIdOrUsername(id, null);
        String p = SecureUtil.md5(SecureUtil.sha256(oldPassword) + user.getSalt() + user.getUsername());
        AssertUtils.isTrue(p.equals(user.getPassword()), "旧密码错误");
        String salt = SecureUtil.md5(SecureUtil.sha256(IdUtil.simpleUUID()));
        String password = SecureUtil.md5(SecureUtil.sha256(newPassword) + salt + user.getUsername());
        return sysUserMapper.updatePassword(id, password, salt);
    }

    @Override
    public int updatePassword(String id, String newPassword) {
        SysUserEntity user = sysUserMapper.selectOneByIdOrUsername(id, null);
        AssertUtils.notNull(user, "用户不存在");
        String salt = SecureUtil.md5(SecureUtil.sha256(IdUtil.simpleUUID()));
        String password = SecureUtil.md5(SecureUtil.sha256(newPassword) + salt + user.getUsername());
        return sysUserMapper.updatePassword(id, password, salt);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String insertSelective(SysUserEntity record) {
        String username = record.getUsername();
        AssertUtils.notBlank(username, "用户名不能为空");
        AssertUtils.notBlank(record.getPassword(), "密码不能为空");
        SysUserEntity user = sysUserMapper.selectOneByIdOrUsername(null, username);
        AssertUtils.isTrue(user == null, "用户名已经存在");
        String salt = SecureUtil.md5(SecureUtil.sha256(IdUtil.simpleUUID()));
        String password = SecureUtil.md5(SecureUtil.sha256(record.getPassword()) + salt + username);
        record.setSalt(salt);
        record.setPassword(password);
        String userId = Snowflake.uuid();
        record.setId(userId);
        sysUserMapper.insertSelective(record);
        return userId;
    }

    @Override
    public String updateByPrimaryKeySelective(SysUserEntity record) {
        AssertUtils.notBlank(record.getId(), "用户编号不能为空");
        sysUserMapper.updateByPrimaryKeySelective(record);
        return record.getId();
    }

    @Override
    public List<SysUserDetail> findByAll(SysUserDetail detail) {
        return sysUserMapper.findByAll(detail);
    }


}







