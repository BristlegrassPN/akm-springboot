package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.domain.PageQuery;
import com.akm.springboot.core.domain.PageResult;
import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysUserDetail;
import com.akm.springboot.web.domain.sys.SysUserEntity;
import com.akm.springboot.web.service.sys.SysUserRoleService;
import com.akm.springboot.web.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/sys/user")
public class SysUserApi {

    private SysUserService sysUserService;
    private SysUserRoleService sysUserRoleService;

    @Autowired
    SysUserApi(SysUserService sysUserService, SysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysUserRoleService = sysUserRoleService;
    }


    @ApiOperation("登陆")
    @PostMapping("/open/login")
    String login(@ApiParam(value = "用户名", required = true) @RequestParam String username,
                 @ApiParam(value = "密码", required = true) @RequestParam String password,
                 @ApiParam(value = "客户端类型", required = true, defaultValue = "1") @RequestParam Byte clientType) {
        return sysUserService.login(username, password, clientType);
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/public/self")
    String self() {
        return "测试";
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/public/updatePassword")
    int updatePassword(@ApiParam(value = "用户编号", required = true) @RequestParam String id,
                       @ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
                       @ApiParam(value = "新密码", required = true) @RequestParam String newPassword) {
        return sysUserService.updatePassword(id, oldPassword, newPassword);
    }

    @ApiOperation("管理员修改密码")
    @PostMapping("/op/updatePassword")
    int updatePassword(@ApiParam(value = "用户编号", required = true) @RequestParam String id,
                       @ApiParam(value = "新密码", required = true) @RequestParam String newPassword) {
        return sysUserService.updatePassword(id, newPassword);
    }

    @ApiOperation(value = "新增/修改,返回用户id", notes = "由于数据库id是雪花算法生成的纯数字，直接返回String类型，前端js处理会以为是Number类型，由于数值过大，会存在精度丢失问题，所以这里返回Map")
    @PostMapping("/op/insertOrUpdate")
    Map<String, String> insertOrUpdate(@RequestBody SysUserEntity record) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(record.getId())) {
            map.put("userId", sysUserService.insertSelective(record));
        } else {
            map.put("userId", sysUserService.updateByPrimaryKeySelective(record));
        }
        return map;
    }

    @ApiOperation("根据编号删除用户")
    @DeleteMapping("/op/del")
    int del(@ApiParam(value = "用户编号", required = true) @RequestParam String id) {
        return sysUserService.deleteByPrimaryKey(id);
    }


    @ApiOperation("给用户分配角色")
    @PostMapping("/op/updateRoleByUserId")
    int updateRoleByUserId(@ApiParam(required = true) @RequestBody List<String> roleIdList,
                           @ApiParam(value = "用户编号", required = true) @RequestParam String userId) {
        AssertUtils.notBlank(userId, "编号不能为空");
        return sysUserRoleService.updateRoleByUserId(roleIdList, userId);
    }

    @ApiOperation("分页查询")
    @PostMapping("/view/findPage")
    PageResult<SysUserDetail> findPage(@RequestBody PageQuery<SysUserDetail> pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
        List<SysUserDetail> list = sysUserService.findByAll(pageQuery.getCondition());
        return new PageResult<>(list);
    }


}