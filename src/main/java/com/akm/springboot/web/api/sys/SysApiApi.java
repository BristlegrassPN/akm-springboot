package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.exception.BusinessException;
import com.akm.springboot.web.domain.sys.SysApi;
import com.akm.springboot.web.service.sys.SysApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"后台接口管理api"})
@RestController
@RequestMapping("/sys/api")
public class SysApiApi {

    private SysApiService sysApiService;

    @Autowired
    SysApiApi(SysApiService service) {
        this.sysApiService = service;
    }

    @ApiOperation("新增/修改")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody SysApi record) {
        return sysApiService.insertOrUpdateSelective(record);
    }


    @ApiOperation("查询所有api")
    @PostMapping("/view/findAll")
    List<SysApi> findAll() {
        return sysApiService.findByAll(new SysApi());
    }

    @ApiOperation("根据id批量删除")
    @DeleteMapping("/op/batchDel")
    int batchDel(@RequestBody List<String> idList) {
        if (idList.isEmpty()) {
            throw new BusinessException("删除的编号不能为空");
        }
        return sysApiService.updateDelById(idList);
    }

}