# Hutool 常用工具类整理

这是一份独立的 Hutool 常用方法速查文档，面向日常 Java Web 开发。内容优先覆盖高频、实用、容易直接上手的方法，并补充少量常见扩展类。

## 1. 字符串处理

常用类：

- `cn.hutool.core.util.StrUtil`

高频方法：

- `StrUtil.isBlank(str)`：判断是否为 `null`、空字符串或全空白字符
- `StrUtil.isNotBlank(str)`：和 `isBlank` 相反
- `StrUtil.isEmpty(str)`：只判断 `null` 或空字符串，不会忽略空格
- `StrUtil.isNotEmpty(str)`：和 `isEmpty` 相反
- `StrUtil.equals(a, b)`：安全比较两个字符串
- `StrUtil.format("name = {}", name)`：占位符格式化
- `StrUtil.sub(str, start, end)`：安全截取子串
- `StrUtil.subBefore(str, ".", false)`：截取分隔符前的内容
- `StrUtil.subAfter(str, ".", false)`：截取分隔符后的内容
- `StrUtil.removePrefix(str, "pre")`：移除前缀
- `StrUtil.removeSuffix(str, ".txt")`：移除后缀
- `StrUtil.upperFirst(str)`：首字母大写
- `StrUtil.lowerFirst(str)`：首字母小写
- `StrUtil.join(",", list)`：拼接字符串

示例：

```java
if (StrUtil.isBlank(username)) {
    throw new RuntimeException("用户名不能为空");
}

String result = StrUtil.format("用户 {} 登录成功", username);
String shortName = StrUtil.sub(appName, 0, 12);
```

使用建议：

- 判空优先区分 `isBlank` 和 `isEmpty`
- 对用户输入做校验时，通常更适合 `isBlank`

## 2. 集合处理

常用类：

- `cn.hutool.core.collection.CollUtil`

高频方法：

- `CollUtil.isEmpty(collection)`：判断集合是否为 `null` 或空
- `CollUtil.isNotEmpty(collection)`：和 `isEmpty` 相反
- `CollUtil.newArrayList()`：快速创建 `ArrayList`
- `CollUtil.newHashSet()`：快速创建 `HashSet`
- `CollUtil.getFirst(list)`：获取第一个元素
- `CollUtil.getLast(list)`：获取最后一个元素
- `CollUtil.join(list, ",")`：集合拼接为字符串
- `CollUtil.contains(collection, obj)`：判断元素是否存在
- `CollUtil.emptyIfNull(collection)`：`null` 转空集合，减少空指针判断

示例：

```java
if (CollUtil.isEmpty(appList)) {
    return new ArrayList<>();
}

List<String> names = CollUtil.newArrayList("Tom", "Jerry");
String text = CollUtil.join(names, ",");
```

使用建议：

- 如果入参可能为 `null`，`CollUtil.isEmpty()` 比直接 `list.isEmpty()` 更安全
- 需要统一空集合返回时，`emptyIfNull()` 很实用

## 3. Map 处理

常用类：

- `cn.hutool.core.map.MapUtil`

高频方法：

- `MapUtil.isEmpty(map)`：判断 `Map` 是否为空
- `MapUtil.isNotEmpty(map)`：和 `isEmpty` 相反
- `MapUtil.newHashMap()`：快速创建 `HashMap`
- `MapUtil.of("key", value)`：快速创建单个键值对的 `Map`
- `MapUtil.getStr(map, "name")`：安全获取字符串值
- `MapUtil.getInt(map, "age")`：安全获取整型值

示例：

```java
Map<String, Object> map = MapUtil.of("id", 1L);
if (MapUtil.isNotEmpty(map)) {
    System.out.println(MapUtil.getStr(map, "id"));
}
```

## 4. 对象与 Bean 处理

常用类：

- `cn.hutool.core.bean.BeanUtil`
- `cn.hutool.core.util.ObjectUtil`

### 4.1 BeanUtil

高频方法：

- `BeanUtil.copyProperties(source, target)`：复制同名属性
- `BeanUtil.copyToList(list, TargetClass.class)`：批量转换对象列表
- `BeanUtil.beanToMap(bean)`：Bean 转 `Map`
- `BeanUtil.fillBeanWithMap(map, bean, false)`：Map 填充到 Bean

示例：

```java
User user = new User();
BeanUtil.copyProperties(userAddRequest, user);
```

使用建议：

- DTO 转 Entity、Entity 转 VO 时很高频
- 字段名不一致时不能完全依赖它，需要手动补字段

### 4.2 ObjectUtil

高频方法：

- `ObjectUtil.isNull(obj)`：判断对象是否为 `null`
- `ObjectUtil.isNotNull(obj)`：和 `isNull` 相反
- `ObjectUtil.equal(a, b)`：安全比较对象
- `ObjectUtil.defaultIfNull(obj, defaultValue)`：为空时返回默认值
- `ObjectUtil.defaultIfEmpty(str, defaultValue)`：字符串空时给默认值

示例：

```java
if (ObjectUtil.equal(userId, ownerId)) {
    System.out.println("是本人操作");
}

String nickname = ObjectUtil.defaultIfNull(name, "无名");
```

使用建议：

- 对对象判等时，`ObjectUtil.equal()` 和 `Objects.equals()` 都可以
- 如果项目已经大量使用 JDK 工具，判等也可以优先用 `Objects.equals()`

## 5. 数组处理

常用类：

- `cn.hutool.core.util.ArrayUtil`

高频方法：

- `ArrayUtil.isEmpty(arr)`：判断数组是否为空
- `ArrayUtil.isNotEmpty(arr)`：和 `isEmpty` 相反
- `ArrayUtil.contains(arr, value)`：判断数组中是否包含元素
- `ArrayUtil.join(arr, ",")`：数组拼接为字符串
- `ArrayUtil.sub(arr, start, end)`：截取子数组
- `ArrayUtil.firstNonNull(arr)`：获取第一个非空元素

示例：

```java
if (ArrayUtil.contains(roleList, "admin")) {
    System.out.println("管理员");
}
```

## 6. 日期时间处理

常用类：

- `cn.hutool.core.date.DateUtil`
- `cn.hutool.core.date.DateTime`

高频方法：

- `DateUtil.date()`：获取当前时间对象
- `DateUtil.now()`：获取当前时间字符串
- `DateUtil.today()`：获取今天日期字符串
- `DateUtil.parse("2026-05-03 10:00:00")`：字符串转时间
- `DateUtil.format(date, "yyyy-MM-dd HH:mm:ss")`：时间格式化
- `DateUtil.offsetDay(date, 1)`：加一天
- `DateUtil.offsetHour(date, -2)`：减两小时
- `DateUtil.beginOfDay(date)`：当天开始时间
- `DateUtil.endOfDay(date)`：当天结束时间
- `DateUtil.betweenDay(start, end, true)`：相差天数
- `DateUtil.between(start, end, DateUnit.MINUTE)`：相差分钟数

示例：

```java
DateTime now = DateUtil.date();
String today = DateUtil.today();
DateTime nextDay = DateUtil.offsetDay(now, 1);
```

使用建议：

- 中小项目做常规日期处理非常方便
- 如果项目已经深度使用 Java 8 `LocalDateTime`，要注意风格统一

## 7. JSON 处理

常用类：

- `cn.hutool.json.JSONUtil`
- `cn.hutool.json.JSONObject`
- `cn.hutool.json.JSONArray`

高频方法：

- `JSONUtil.toJsonStr(obj)`：对象转 JSON 字符串
- `JSONUtil.parseObj(jsonStr)`：字符串转 `JSONObject`
- `JSONUtil.parseArray(jsonStr)`：字符串转 `JSONArray`
- `JSONUtil.toBean(jsonStr, User.class)`：JSON 转对象
- `JSONUtil.isTypeJSON(str)`：判断是否是 JSON
- `JSONUtil.isJsonObj(str)`：判断是否是 JSON 对象
- `JSONUtil.isJsonArray(str)`：判断是否是 JSON 数组

示例：

```java
String json = JSONUtil.toJsonStr(user);
User userObj = JSONUtil.toBean(json, User.class);
```

使用建议：

- 对轻量 JSON 处理很方便
- 如果项目里已经统一用 Jackson，建议明确边界，避免混用过多

## 8. 加密与摘要

常用类：

- `cn.hutool.crypto.SecureUtil`

高频方法：

- `SecureUtil.md5(data)`：MD5 摘要
- `SecureUtil.sha1(data)`：SHA1 摘要
- `SecureUtil.sha256(data)`：SHA256 摘要
- `SecureUtil.hmacSha256(key).digestHex(data)`：HMAC-SHA256
- `SecureUtil.aes(keyBytes)`：创建 AES 工具

示例：

```java
String md5 = SecureUtil.md5(password);
String sha256 = SecureUtil.sha256(content);
```

使用建议：

- 密码存储不要直接用简单 MD5，至少要加盐
- 生产环境更建议使用更稳妥的密码方案，如 `BCrypt`

## 9. 随机数与 UUID

常用类：

- `cn.hutool.core.util.RandomUtil`
- `cn.hutool.core.lang.UUID`
- `cn.hutool.core.lang.Snowflake`
- `cn.hutool.core.util.IdUtil`

高频方法：

- `RandomUtil.randomInt(min, max)`：随机整数
- `RandomUtil.randomLong(min, max)`：随机长整型
- `RandomUtil.randomString(8)`：随机字符串
- `RandomUtil.randomNumbers(6)`：随机数字串
- `UUID.randomUUID().toString()`：生成 UUID
- `IdUtil.simpleUUID()`：无横杠 UUID
- `IdUtil.fastUUID()`：快速生成 UUID
- `IdUtil.getSnowflake(workerId, datacenterId)`：获取雪花算法实例

示例：

```java
String code = RandomUtil.randomNumbers(6);
String uuid = IdUtil.simpleUUID();
```

## 10. 文件与 IO

常用类：

- `cn.hutool.core.io.FileUtil`
- `cn.hutool.core.io.IoUtil`

高频方法：

- `FileUtil.exist(path)`：判断文件是否存在
- `FileUtil.file(path)`：根据路径创建 `File`
- `FileUtil.readUtf8String(file)`：读取文本文件
- `FileUtil.writeUtf8String(content, file)`：写入文本文件
- `FileUtil.appendUtf8String(content, file)`：追加文本
- `FileUtil.mkdir(path)`：创建目录
- `FileUtil.del(path)`：删除文件或目录
- `FileUtil.extName(fileName)`：获取后缀名
- `FileUtil.mainName(fileName)`：获取主文件名
- `IoUtil.close(closeable)`：安全关闭流

示例：

```java
File file = FileUtil.file("data/test.txt");
FileUtil.writeUtf8String("hello", file);
String content = FileUtil.readUtf8String(file);
```

使用建议：

- 处理简单文件读写很省事
- 删除操作要谨慎，尤其是目录递归删除

## 11. 类型转换

常用类：

- `cn.hutool.core.convert.Convert`

高频方法：

- `Convert.toStr(obj)`：转字符串
- `Convert.toInt(obj)`：转整型
- `Convert.toLong(obj)`：转长整型
- `Convert.toBool(obj)`：转布尔值
- `Convert.toList(String.class, data)`：转列表
- `Convert.toDate(obj)`：转日期

示例：

```java
Integer age = Convert.toInt("18");
Long userId = Convert.toLong(requestMap.get("userId"));
```

使用建议：

- 处理接口参数、通用 `Map`、配置值时很方便
- 但要注意转换失败后的默认行为，关键字段仍建议显式校验

## 12. 反射与类工具

常用类：

- `cn.hutool.core.util.ClassUtil`
- `cn.hutool.core.util.ReflectUtil`

高频方法：

- `ClassUtil.getClassName(obj, true)`：获取类名
- `ReflectUtil.getFieldValue(obj, "fieldName")`：获取字段值
- `ReflectUtil.setFieldValue(obj, "fieldName", value)`：设置字段值
- `ReflectUtil.invoke(obj, "methodName")`：反射调用方法

使用建议：

- 适合框架型、工具型代码
- 业务代码里不要滥用，反射会降低可读性

## 13. 校验工具

常用类：

- `cn.hutool.core.lang.Validator`

高频方法：

- `Validator.isEmail(str)`：邮箱校验
- `Validator.isMobile(str)`：手机号校验
- `Validator.isUrl(str)`：URL 校验
- `Validator.isCitizenId(str)`：身份证号校验
- `Validator.isNumber(str)`：数字校验

示例：

```java
if (!Validator.isEmail(email)) {
    throw new RuntimeException("邮箱格式错误");
}
```

使用建议：

- 适合做表单参数的基础校验
- 更复杂的业务规则还是建议放在专门的校验逻辑里

## 14. 注解与断言

常用类：

- `cn.hutool.core.lang.Assert`

高频方法：

- `Assert.notNull(obj)`：对象不能为空
- `Assert.notBlank(str)`：字符串不能为空白
- `Assert.isTrue(condition)`：断言条件为真
- `Assert.state(condition)`：状态断言

示例：

```java
Assert.notBlank(appName, "应用名称不能为空");
Assert.isTrue(pageSize > 0, "分页大小必须大于 0");
```

使用建议：

- 适合内部工具方法或基础层防御式编程
- 如果项目已有统一异常体系，要注意断言抛出的异常是否符合规范

## 15. 线程与系统工具

常用类：

- `cn.hutool.core.thread.ThreadUtil`
- `cn.hutool.system.SystemUtil`

高频方法：

- `ThreadUtil.execAsync(runnable)`：异步执行任务
- `ThreadUtil.sleep(ms)`：线程休眠
- `SystemUtil.getOsInfo()`：获取操作系统信息
- `SystemUtil.getUserInfo()`：获取当前用户信息
- `SystemUtil.getHostInfo()`：获取主机信息

使用建议：

- `ThreadUtil` 适合简单异步任务
- 真正的业务异步还是更建议统一线程池管理

## 16. 常见使用建议

- 字符串判空优先用 `StrUtil.isBlank()`
- 集合判空优先用 `CollUtil.isEmpty()`
- DTO / VO / Entity 转换优先用 `BeanUtil.copyProperties()`
- 对象判等优先用 `ObjectUtil.equal()` 或 JDK 的 `Objects.equals()`
- 日期格式化和偏移计算优先用 `DateUtil`
- 轻量 JSON 处理可以用 `JSONUtil`
- 接口入参或 `Map` 数据转换可以用 `Convert`

## 17. 学习优先级建议

如果你刚开始用 Hutool，建议先掌握下面这些：

1. `StrUtil`
2. `CollUtil`
3. `BeanUtil`
4. `ObjectUtil`
5. `DateUtil`
6. `JSONUtil`
7. `Convert`
8. `SecureUtil`

这几个基本已经覆盖大部分 CRUD、参数处理、对象转换、时间处理和常见工具需求。

## 18. 什么时候不建议滥用 Hutool

- 项目已经有统一技术选型时，不要为了方便混入太多不同风格的工具
- JDK 原生写法更清晰时，不必强行替换成工具类
- 密码、安全、加密等关键场景不要只图省事，要优先考虑安全性
- 反射、文件删除、动态转换这些能力很方便，但也更容易引入隐患

## 19. 参考记忆方式

可以先按下面这套记忆：

- 判空字符串：`StrUtil`
- 判空集合：`CollUtil`
- 判空 Map：`MapUtil`
- 拷贝对象：`BeanUtil`
- 比较对象：`ObjectUtil`
- 处理日期：`DateUtil`
- 处理 JSON：`JSONUtil`
- 类型转换：`Convert`
- 随机和 ID：`RandomUtil` / `IdUtil`
- 加密摘要：`SecureUtil`
- 文件读写：`FileUtil`

先把这 11 类用熟，日常开发已经够用了。
