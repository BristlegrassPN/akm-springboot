package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.domain.PageQuery;
import com.akm.springboot.core.domain.PageResult;
import com.akm.springboot.web.domain.sys.SysDict;
import com.akm.springboot.web.service.sys.SysDictService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = {"数据字典api"})
@RestController
@RequestMapping("/sys/dict")
public class SysDictApi {

    private SysDictService sysDictService;

    @Autowired
    SysDictApi(SysDictService service) {
        this.sysDictService = service;
    }

    @ApiOperation("新增/修改")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody SysDict record) {
        return sysDictService.insertOrUpdateSelective(record);
    }

    @ApiOperation("分页查询")
    @PostMapping("/view/findPage")
    PageResult<SysDict> findPage(@RequestBody PageQuery<SysDict> pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
        List<SysDict> list = sysDictService.findByAll(pageQuery.getCondition());
        return new PageResult<>(list);
    }

    @ApiOperation("根据字典类型查询")
    @PostMapping("/view/findByType")
    List<Map<String, Object>> findByType(@ApiParam(value = "字典类型", required = true) @RequestParam String type) {
        return sysDictService.findByType(type);
    }

}