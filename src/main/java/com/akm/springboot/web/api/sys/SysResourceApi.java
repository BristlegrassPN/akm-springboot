package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.web.domain.sys.SysResource;
import com.akm.springboot.web.service.sys.SysResourceApiService;
import com.akm.springboot.web.service.sys.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"前端资源(菜单/按钮)管理"})
@RestController
@RequestMapping("/sys/resource")
public class SysResourceApi {

    private SysResourceService sysResourceService;
    private SysResourceApiService sysResourceApiService;

    @Autowired
    SysResourceApi(SysResourceService sysResourceService, SysResourceApiService sysResourceApiService) {
        this.sysResourceService = sysResourceService;
        this.sysResourceApiService = sysResourceApiService;
    }

    @ApiOperation("新增/修改")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody SysResource record) {
        return sysResourceService.insertOrUpdateSelective(record);
    }


    @ApiOperation("查询所有api")
    @PostMapping("/view/findAll")
    List<SysResource> findAll() {
        return sysResourceService.findByAll(new SysResource());
    }

    @ApiOperation("根据id批量删除")
    @DeleteMapping("/op/batchDel")
    int batchDel(@RequestBody List<String> idList) {
        AssertUtils.notEmpty(idList, "删除的编号不能为空");
        return sysResourceService.batchDel(idList);
    }

    @ApiOperation("给资源分配api")
    @PostMapping("/op/updateApiByResourceId")
    int updateApiByResourceId(@ApiParam(required = true) @RequestBody List<String> apiIdList,
                  @ApiParam(value = "资源编号", required = true) @RequestParam String resourceId) {
        AssertUtils.notBlank(resourceId, "编号不能为空");
        return sysResourceApiService.updateApiByResourceId(apiIdList, resourceId);
    }
}