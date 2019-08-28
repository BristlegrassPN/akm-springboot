package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.web.domain.sys.SysRole;
import com.akm.springboot.web.service.sys.SysRoleResourceService;
import com.akm.springboot.web.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"角色管理"})
@RestController
@RequestMapping("/sys/role")
public class SysRoleApi {

    private SysRoleService sysRoleService;
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    SysRoleApi(SysRoleService sysRoleService, SysRoleResourceService sysRoleResourceService) {
        this.sysRoleService = sysRoleService;
        this.sysRoleResourceService = sysRoleResourceService;
    }

    @ApiOperation("新增/修改")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody SysRole record) {
        return sysRoleService.insertOrUpdateSelective(record);
    }

    @ApiOperation("根据id批量删除")
    @DeleteMapping("/op/batchDel")
    int batchDel(@RequestBody List<String> idList) {
        AssertUtils.notEmpty(idList, "删除的编号不能为空");
        return sysRoleService.batchDel(idList);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/op/updateResourceByRoleId")
    int updateResourceByRoleId(@RequestBody List<String> resourceIdList,
                               @ApiParam(value = "角色编号", required = true) @RequestParam String roleId) {
        AssertUtils.notBlank(roleId, "编号不能为空");
        return sysRoleResourceService.updateResourceByRoleId(resourceIdList, roleId);
    }


    @ApiOperation("查询所有角色")
    @PostMapping("/view/find")
    List<SysRole> find(@ApiParam(value = "启用状态，默认true") @RequestParam(required = false) Boolean enable,
                       @ApiParam(value = "应用类型,对应字典表client_type") @RequestParam(required = false) Byte clientType) {
        return sysRoleService.find(enable, clientType);
    }
}