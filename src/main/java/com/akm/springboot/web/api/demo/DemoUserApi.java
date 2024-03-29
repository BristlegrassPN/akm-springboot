package com.akm.springboot.web.api.demo;

import com.akm.springboot.core.domain.PageQuery;
import com.akm.springboot.core.domain.PageResult;
import com.akm.springboot.web.domain.demo.DemoUser;
import com.akm.springboot.web.service.demo.DemoUserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"用户管理api，用于测试，可以随意增删该查"})
@RestController
@RequestMapping("/demo/user/open")
public class DemoUserApi {

    private DemoUserService demoUserService;

    @Autowired
    DemoUserApi(DemoUserService service) {
        this.demoUserService = service;
    }

    @ApiOperation("新增/修改测试用户")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody DemoUser demoUser) {
        return demoUserService.insertOrUpdateSelective(demoUser);
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/view/findPage")
    PageResult<DemoUser> findPage(@RequestBody(required = false) PageQuery<DemoUser> pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
        List<DemoUser> list = demoUserService.findByAll(pageQuery.getCondition());
        return new PageResult<>(list);
    }

    @ApiOperation("用户列表")
    @PostMapping("/view/findList")
    List<DemoUser> findList(@RequestBody(required = false) DemoUser demoUser) {
        return demoUserService.findByAll(demoUser);
    }

    @ApiOperation("根据id查询用户名")
    @GetMapping("/view/findUsernameById/{id}")
    String findUsernameById(@PathVariable Integer id) {
        return demoUserService.findUsernameById(id);
    }

}
