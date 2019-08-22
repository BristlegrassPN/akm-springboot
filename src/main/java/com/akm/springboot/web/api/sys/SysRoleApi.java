package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.exception.BusinessException;
import com.akm.springboot.core.utils.StringUtils;
import com.akm.springboot.web.domain.sys.SysRole;
import com.akm.springboot.web.service.sys.SysRoleResourceService;
import com.akm.springboot.web.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"前端资源(菜单/按钮)管理"})
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


    @ApiOperation("查询所有角色")
    @PostMapping("/view/findAll")
    List<SysRole> findAll() {
        return sysRoleService.findByAll(new SysRole());
    }

    @ApiOperation("根据id批量删除")
    @DeleteMapping("/op/batchDel")
    int batchDel(@RequestBody List<String> idList) {
        if (idList.isEmpty()) {
            throw new BusinessException("删除的编号不能为空");
        }
        return sysRoleService.batchDel(idList);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/op/updateResource")
    int updateResource(@ApiParam(value = "apiIdList", required = true) @RequestBody List<String> resourceIdList,
                       @ApiParam(value = "资源编号", required = true) @RequestParam String roleId) {
        if (StringUtils.isBlank(roleId)) {
            throw new BusinessException("资源编号不能为空");
        }
        return sysRoleResourceService.updateResource(resourceIdList, roleId);
    }
}