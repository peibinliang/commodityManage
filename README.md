# 技术栈说明

- 持久层：Mybatis
- 视图层：JSP
- 日志：log4j2
- 单元测试：JUnit
- 插件：Swagger Lombok 

---

# 包名说明

- api: 存放API
- service：存放业务类
- dao：存放持久层类
- domain：存放实体类
- core：存放核心类，如常量类，工具类，异常类，全局实体类等
- interceptor：存放拦截器
- filter：存放过滤器
- config: 存放配置类，如Swagger配置类

---

# 配置说明
所有配置文件全部位于src/main/resources目录下

- application.properties：项目全局配置文件，项目中所有的配置信息都可列入此文件
- spring.xml：配置spring上下文

- spring-mybatis.xml：配置spring集成mybatis所需信息
- log4j2.xml：配置log4j2日志


---


# 持久层(mapper)说明

#### 实体类、Example类、Mapper类的生成
框架中集成了MyBatis Generator，可通过MyBatis Generator来生成。

---

# 控制层(controller)说明

#### 控制层基类
BaseController: 位于com.commoditymanage.api

#### 控制层实现

- 所有Controller类都应该继承BaseController，以获取全局处理
- Controller无需手动捕获异常，异常将进行全局捕获（将根据接口性质返回错误页面或异常JSON对象）

# 视图层说明

- 页面全部存放于src/main/webapp/WEB-INF/pages目录下，其中位于pages/common/head.jsp为通用jsp，在这里处理了上下文路径等。
- 静态资源存放于src/main/webapp/static目录下

# 单元测试

#### 单元测试基类 
BaseTest：位于test目录下的com.commoditymanage.service包

#### 单元测试实现
编写单元测试类，继承BaseTest即可，测试结果通过BaseTest基类中的print成员方法输出。

# 全局异常处理

通过BaseController的exceptionHandle方法捕获全局异常，当访问一个页面接口出错时，将自动跳转至错误页面，当访问一个数据处理接口出错时，将自动返回异常JSON对象。
