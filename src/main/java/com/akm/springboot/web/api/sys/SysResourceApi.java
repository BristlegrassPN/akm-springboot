package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.exception.BusinessException;
import com.akm.springboot.web.domain.sys.SysResource;
import com.akm.springboot.web.service.sys.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"前端资源(菜单/按钮)管理"})
@RestController
@RequestMapping("/sys/resource")
public class SysResourceApi {

    private SysResourceService sysResourceService;

    @Autowired
    SysResourceApi(SysResourceService service) {
        this.sysResourceService = service;
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
    int batchDel(@RequestBody List<String> ids) {
        if (ids.isEmpty()) {
            throw new BusinessException("删除的编号不能为空");
        }
        return sysResourceService.updateDelById(ids);
    }
}