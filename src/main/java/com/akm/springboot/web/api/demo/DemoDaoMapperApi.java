package com.akm.springboot.web.api.demo;

import com.akm.springboot.core.domain.PageResult;
import com.akm.springboot.web.mapper.demo.DemoUserMapper;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = {"api直连mapper"})
@RestController
@RequestMapping("/demo/dao/open")
public class DemoDaoMapperApi {

    @Resource
    private DemoUserMapper demoUserMapper;

    @ApiOperation("新增/修改")
    @PostMapping("/op/insertOrUpdate")
    int insertOrUpdate(@RequestBody Map<String, Object> param) {
        return demoUserMapper.insertOrUpdateSelective(param);
    }

    @ApiOperation("查询所有")
    @PostMapping("/view/findList")
    List<Map<String, Object>> findList(@RequestBody(required = false) Map<String, Object> param) {
        return demoUserMapper.findList(param);
    }

    @ApiOperation("分页查询")
    @PostMapping("/view/findPage")
    PageResult<Map<String, Object>> findPage(@RequestBody Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        return new PageResult<>(demoUserMapper.findList(param));
    }
}
