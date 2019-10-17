package com.akm.springboot.web.service.sys;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.akm.springboot.core.config.AkmConstants;
import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.MapBuilder;
import com.akm.springboot.core.utils.Snowflake;
import com.akm.springboot.web.domain.sys.SysUserDetail;
import com.akm.springboot.web.domain.sys.SysUserEntity;
import com.akm.springboot.web.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    private SysRoleService sysRoleService;

    @Autowired
    SysUserServiceImpl(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Override
    public Map<String, Object> login(String username, String password, Byte clientType) {
        AssertUtils.notBlank(username, "用户名不允许为空");
        AssertUtils.notBlank(password, "密码不允许为空");
        AssertUtils.notNull(clientType, "客户端类型不允许为空");
        SysUserEntity user = sysUserMapper.selectOneByIdOrUsername(null, username);
        AssertUtils.notNull(user, "用户名不存在");
        AssertUtils.isTrue(user.getEnable(), "账号已被禁用");
        AssertUtils.isTrue(!user.getDel(), "账号已失效");
        if (user.getExpiredTime() != null) {
            AssertUtils.isTrue(new Date().compareTo(user.getExpiredTime()) < 0, "账号已过期");
        }
        String p = SecureUtil.md5(SecureUtil.sha256(password) + user.getSalt() + username);
        AssertUtils.isTrue(p.equals(user.getPassword()), "密码错误");
        // 清楚敏感数据
        user.setPassword(null);
        user.setSalt(null);

        // 用户id
        String userId = user.getId();

        // 用户登陆/重新登陆，删除已存在的缓存数据
        CacheUtils.delByPattern(userId);

        String token = userId + Snowflake.uuid();

        // 7 * 24 * 60 * (60000L) 7天
        // 15 * (60000L) 15分钟
        long timeout = 15 * (60000L);

        // 缓存token和用户id关系
//        StringCacheUtils.set(token, userId, timeout, TimeUnit.MILLISECONDS);

        // 获取登陆用户所拥有的角色
        List<Map<String,String>> roleList = sysRoleService.findRoleByUser(userId, clientType);
        String currentRoleId = roleList.isEmpty() ? "" : roleList.get(0).get("id");
        Map<String, String> userInfo = MapBuilder.createString()
                .put(AkmConstants.TOKEN, token)
                .put(AkmConstants.CLIENT_TYPE, String.valueOf(clientType))
                .put(AkmConstants.USER_ID, userId)
                .put(AkmConstants.USERNAME, user.getUsername())
                .put(AkmConstants.CURRENT_ROLE_ID, currentRoleId)
                .build(); // 指定一个角色为当前角色

        CacheUtils.set(token, userInfo, timeout); // 常用数据缓存

        return MapBuilder.createDefault()
                .put(AkmConstants.TOKEN, token)
                .put(AkmConstants.CURRENT_ROLE_ID, currentRoleId)
                .put("roleList", roleList)
                .put("name", user.getName()) // 真实姓名
                .build();
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







