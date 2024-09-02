# 演示向patch-spring工程注入

执行package生成jar包，在path-spring启动时增加VM Options

# 预期结果

## 跳过了权限校验

http://localhost:8080/sayHello?name=nick

result:

```json
{
  "name": "nick"
}
```

## 增加响应头

以t_开头的响应

|      Name      |                      Value                       |
|:--------------:|:------------------------------------------------:|
|      t_id      |       973a961b-821a-48c5-a071-772c183979ed       |
|     t_m_n      |                      hello                       |
|     t_c_n      | cn.nickdlk.patchspring.controller.testController |
|   Keep-Alive   |                    timeout=60                    |
|   Connection   |                    keep-alive                    |
| Content-Length |                        10                        |
|      Date      |          Mon, 02 Sep 2024 08:01:49 GMT           |
|  Content-Type  |                 application/json                 |

## 跳过定时任务

## 链路追踪和JVM监控

```
init: 960MB	 max: 15296MB	 used: 136MB	 committed: 552MB	 use rate: 24%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 72MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：a67d55b9-fe14-4551-b911-92cd0169fb9d cn.nickdlk.patchspring.interceptor.LicenseInterceptor.preHandle 耗时：0ms
MyAdvice: cn.nickdlk.patchspring.controller.testController.sayHello

init: 960MB	 max: 15296MB	 used: 136MB	 committed: 552MB	 use rate: 24%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 72MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：dcb4cdd8-bd3b-481d-97ee-6ce83c7321fa cn.nickdlk.patchspring.dao.User.setName 耗时：0ms

init: 960MB	 max: 15296MB	 used: 136MB	 committed: 552MB	 use rate: 24%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 72MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：dcb4cdd8-bd3b-481d-97ee-6ce83c7321fa cn.nickdlk.patchspring.service.DemoService.sayHello 耗时：0ms

init: 960MB	 max: 15296MB	 used: 136MB	 committed: 552MB	 use rate: 24%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 72MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：dcb4cdd8-bd3b-481d-97ee-6ce83c7321fa cn.nickdlk.patchspring.controller.testController.sayHello 耗时：1ms
16:53:30.707 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Using 'application/json', given [application/json, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8] and supported [application/json, application/*+json]

init: 960MB	 max: 15296MB	 used: 200MB	 committed: 552MB	 use rate: 36%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 73MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：a1f5e473-0e50-4663-ac2d-db8849ee0ce8 cn.nickdlk.patchspring.dao.User.getName 耗时：0ms

init: 960MB	 max: 15296MB	 used: 200MB	 committed: 552MB	 use rate: 36%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 73MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：a1f5e473-0e50-4663-ac2d-db8849ee0ce8 cn.nickdlk.patchspring.dao.User.toString 耗时：0ms
16:53:30.708 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Writing [User(name=nick)]

init: 960MB	 max: 15296MB	 used: 216MB	 committed: 552MB	 use rate: 39%
init: 2MB	 max: 0MB	 used: 67MB	 committed: 73MB	 use rate: 92%

name: G1 Young Generation	 count:27	 took:62	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
name: G1 Old Generation	 count:0	 took:0	 pool name:[G1 Eden Space, G1 Survivor Space, G1 Old Gen]
-------------------------------------------------------------------------------------------------
链路追踪(MQ)：72970a67-dd72-4ef1-b98f-7ee336d5d6d5 cn.nickdlk.patchspring.dao.User.getName 耗时：0ms
16:53:30.731 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - Completed 200 OK
```

```
[0]cn.nickdlk.patchspring.interceptor.LicenseInterceptor.preHandle(entry)  
[1]cn.nickdlk.patchspring.interceptor.LicenseInterceptor.preHandle(exit)  
[2]cn.nickdlk.patchspring.controller.testController.sayHello(entry)  
[3]cn.nickdlk.patchspring.service.DemoService.sayHello(entry)  
[4]cn.nickdlk.patchspring.dao.User.setName(entry)  
[5]cn.nickdlk.patchspring.dao.User.setName(exit)  
[6]cn.nickdlk.patchspring.service.DemoService.sayHello(exit)  
[7]cn.nickdlk.patchspring.controller.testController.sayHello(exit)  
[8]cn.nickdlk.patchspring.dao.User.toString(entry)  
[9]cn.nickdlk.patchspring.dao.User.getName(entry)  
[10]cn.nickdlk.patchspring.dao.User.getName(exit)  
[11]cn.nickdlk.patchspring.dao.User.toString(exit)  
[12]cn.nickdlk.patchspring.dao.User.getName(entry)  
[13]cn.nickdlk.patchspring.dao.User.getName(exit)  
```

这个日志可以优化一下，增加层级和时间，就可以知道整条调用链路了

# 参考

LogAdvice： https://blog.csdn.net/who7708/article/details/113185883

Track: https://bugstack.cn/md/bytecode/agent/2019-07-10-%E5%9F%BA%E4%BA%8EJavaAgent%E7%9A%84%E5%85%A8%E9%93%BE%E8%B7%AF%E7%9B%91%E6%8E%A7%E4%B8%80%E3%80%8A%E5%97%A8%EF%BC%81JavaAgent%E3%80%8B.html