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


# 参考

LogAdvice： https://blog.csdn.net/who7708/article/details/113185883