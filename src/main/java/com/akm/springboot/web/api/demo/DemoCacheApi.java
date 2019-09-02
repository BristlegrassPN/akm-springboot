package com.akm.springboot.web.api.demo;


import com.akm.springboot.core.config.AkmPropsConfig;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.SpringContextHolder;
import com.akm.springboot.core.utils.StringCacheUtils;
import com.akm.springboot.web.domain.sys.SysUserDetail;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = {"系统缓存测试"})
@RestController
@RequestMapping("/demo/cache/open")
public class DemoCacheApi {

    @PostMapping(value = "/cacheType", produces = "text/plain;charset=UTF-8")
    String cacheType() {
        AkmPropsConfig kitProps = SpringContextHolder.getBean(AkmPropsConfig.class);
        return "当前缓存类型：" + kitProps.getCacheType();
    }

    @PostMapping(value = "/setString", produces = "text/plain;charset=UTF-8")
    public String setString() {
        StringCacheUtils.set("string", "测试");
        return "保存成功,请访问/getString查询";
    }

    @PostMapping(value = "/getString", produces = "text/plain;charset=UTF-8")
    public String getString() {
        String string = StringCacheUtils.get("string");
        System.out.println(string);
        return string;
    }

    @PostMapping(value = "/setObj", produces = "text/plain;charset=UTF-8")
    public String setObj() {
        SysUserDetail sysUserDetail = new SysUserDetail();
        sysUserDetail.setId("1");
        sysUserDetail.setName("张三");
        sysUserDetail.setExpiredTime(new Date());
        CacheUtils.setnx("obj", sysUserDetail);
        System.out.println(sysUserDetail);
        return "保存成功,请访问/getObj查询";
    }

    @PostMapping(value = "/getObj", produces = "text/plain;charset=UTF-8")
    public SysUserDetail getObj() {
        Object obj = CacheUtils.get("obj");
        SysUserDetail sysUserDetail = (SysUserDetail) obj;
        System.out.println(sysUserDetail);
        return sysUserDetail;
    }

    @PostMapping(value = "/setList", produces = "text/plain;charset=UTF-8")
    public String setList() {
        List<SysUserDetail> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SysUserDetail sysUserDetail = new SysUserDetail();
            sysUserDetail.setId(String.valueOf(i));
            sysUserDetail.setName("张三");
            sysUserDetail.setExpiredTime(new Date());
            list.add(sysUserDetail);
        }
        CacheUtils.setnx("list", list);
        return "保存成功,请访问/getList查询";
    }

    @PostMapping(value = "/getList", produces = "text/plain;charset=UTF-8")
    public List<SysUserDetail> getList() {
        Object obj = CacheUtils.get("list");
        List<SysUserDetail> list = null;
        if (obj != null) {
            list = (List<SysUserDetail>) obj;
            System.out.println(list.size());
        }
        return list;
    }

    @PostMapping(value = "/delByStringCacheUtils", produces = "text/plain;charset=UTF-8")
    public String delByStringCacheUtils(@RequestParam String key) {
        StringCacheUtils.del(key);
        return "操作完成";
    }

    @PostMapping(value = "/delByCacheUtils", produces = "text/plain;charset=UTF-8")
    public String delByCacheUtils(@RequestParam String key) {
        CacheUtils.del(key);
        return "操作完成";
    }

}
