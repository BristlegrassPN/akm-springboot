package com.akm.springboot.web.api.demo;

import com.akm.springboot.core.exception.BusinessException;
import com.akm.springboot.web.domain.demo.DemoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"全局异常处理测试"})
@RestController
@RequestMapping("/demo/exception/open")
public class DemoExceptionApi {
    private static final Logger logger = LoggerFactory.getLogger(DemoExceptionApi.class);

    @ApiOperation(value = "自定义业务异常：BusinessException", notes = "BusinessException：用于业务判断或try catch捕获后抛出的异常,前端可直接显示msg给用户")
    @GetMapping("/customer")
    void customer() {
        throw new BusinessException("我是异常内容");
    }

    @ApiOperation("测试参数转换异常")
    @PostMapping("/paramError")
    void paramError(@RequestBody DemoUser demoUser) {
        System.out.println(demoUser);
    }

    @ApiOperation("测试参数类型异常:参数为非int类型报错")
    @GetMapping("/paramTypeError")
    void paramTypeError(@RequestParam Integer num) {
        System.out.println(num);
    }

    @ApiOperation("除0异常")
    @GetMapping("/byZero")
    void byZero() {
        System.out.println(10 / 0);
    }

    @ApiOperation("空指针异常")
    @GetMapping("/nullPointer")
    void nullPointer() {
        Integer i = null;
        System.out.println(i.equals(1));
    }

}
