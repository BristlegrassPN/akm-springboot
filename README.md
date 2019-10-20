# akm-springboot
* 基于前后端分离的开发方式，提供api接口，供客户端调用
* sql脚本在doc目录下，管理员账号密码admin/123456

### hutool工具类
https://www.hutool.club/docs  
https://github.com/looly/hutool

### 代码生成器：MyBatisCodeHelper-Pro
https://github.com/gejun123456/MyBatisCodeHelper-Pro  
https://gejun123456.github.io/MyBatisCodeHelper-Pro

### 分页插件：Mybatis-PageHelper
https://github.com/abel533/MyBatis-Spring-Boot  
https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md

### 日志
https://www.cnblogs.com/bigdataZJ/p/springboot-log.html  
https://www.jianshu.com/p/1fa12b92d5c4

### 统一异常处理
https://blog.csdn.net/qq_18603599/article/details/81168927  
https://blog.csdn.net/qq_36922927/article/details/82026683  
https://www.cnblogs.com/owenma/p/9289607.html  
https://www.jianshu.com/p/5c28a26e0e3e

## 框架约定
#### 数据库
* 数据库使用mysql数据库，字符串utf8mb4，排序规则utf8mb4_general_ci
* 主表id均使用varchar(20)，值为雪花算法生成的id，可以使用`Snowflake.uuid()`生成
* 表名全部小写，多个单词用下划线分割，按模块使用统一前缀，如：`sys_`
* 字段全部小写，多个单词用下划线分割，主业务表需要添加create_time，update_time，del等字段，参考sys_表
* 外键暂不添加外键索引，外键备注必要时添加上对应的表名

### 缓存
* 框架集成了本地缓存和redis缓存，可以在`application.yml`中配置`akm.cache-type`参数，开发阶段配置`local`，生产环境使用`redis`
* 缓存的使用通过`CacheUtils`和`StringCacheUtils`，优先使用`StringCacheUtils`，注意缓存的key需要添加业务前缀，前缀需要在`RedisKeys.java`中配置，如
`RedisKeys.ROLE_URI.concat(Snowflake.uuid()`，具体使用可参考:
    ```
    public List<String> getUriByRoleId(String roleId) {
        // 先尝试从缓存获取, 缓存不存在才从数据库获取,更新或删除用户角色，需要清缓存
        return CacheUtils.cacheAndGet(RedisKeys.ROLE_URI.concat(roleId), -1, () -> sysApiMapper.getUriByRoleId(roleId));
    }
    ```
  
### 权限拦截器
* 在`AuthorizationInterceptor.java`中做权限校验，根据访问的url验证用户是否有访问api权限
* 本框架针对api的uri有4种约定
  > 1. uri包含`/view/`：表示该接口是查询类，分配权限时可以使用通配符“*”，如`/user/view/**`，表示用户拥有/user下所有查询类接口权限
  > 1. uri包含`/op/`：表示该接口是操作类
  > 1. uri包含`/open/`：表示该接口可以直接访问，不需要鉴权   
  > 1. uri包含`/public/`：表示该接口只要登陆则可以访问
* 注意：用户有多个角色时，同时只允许一个角色访问系统，在登陆接口指定当前用户默认角色，所以前端需要实现切换角色功能，后端缓存种也需要响应修改


### 异常处理
* 在`GlobalExceptionHandler.java`中做全局异常处理，具体处理逻辑可以查看该类
* 异常按处理阶段分4种情况
  > 1. 请求未能到达系统，此时系统无法处理该异常，前端响应状态码一般为0，可以提示系统繁忙或网络出错，如系统未启动
  > 1. 请求被系统接收，系统不能处理，如接口不存在，响应状态码为404
  > 1. 请求进入接口失败，如参数不存在，参数解析失败，请求方法不被支持，系统统一返回响应码500
  > 1. 请求成功进入接口发生异常，根据是否捕获或处理分两种请求，如果未捕获或处理返回响应码500，否在抛出业务异常BusinessException，
                 >目前业务业务异常约定返回的状态码是510(可以在GlobalExceptionHandler中修改)，前端根据该状态码可以弹出友好提示
* 业务异常处理形式如下
    ```
      try {
         // 业务逻辑
      } catch (Exception e) {
         e.printStackTrace();
         throw new BusinessException("某某业务处理失败");
      }
    ```
    ```
      // 使用AssertUtils
      AssertUtils.notBlank(password, "密码不允许为空");
      AssertUtils.notNull(user, "用户名不存在");
    ```
    ```
     // 使用CodeMsg
     AssertUtils.notNull(roleId, CodeMsg.Forbidden);
    ```

### 关于工具类
* 工具类统一放在`com.akm.springboot.core.utils`包下，由于本框架使用了hutool工具类https://hutool.cn/docs/，
该工具库涵盖了系统开发时常用工具方法，所以非必要时无需添加工具类