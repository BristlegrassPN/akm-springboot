package com.akm.springboot.web.api.sys;

import com.akm.springboot.core.domain.PageQuery;
import com.akm.springboot.core.domain.PageResult;
import com.akm.springboot.web.domain.sys.SysDict;
import com.akm.springboot.web.service.sys.SysDictService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    int insertOrUpdate(@RequestBody SysDict SysDict) {
        return sysDictService.insertOrUpdateSelective(SysDict);
    }

    @ApiOperation("分页查询")
    @PostMapping("/view/findPage")
    PageResult<SysDict> findPage(@RequestBody PageQuery<SysDict> pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
        List<SysDict> list = sysDictService.findByAll(pageQuery.getCondition());
        return new PageResult<>(list);
    }

}