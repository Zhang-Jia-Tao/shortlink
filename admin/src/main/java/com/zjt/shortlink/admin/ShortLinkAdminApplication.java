package com.zjt.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SpringBootApplication: 1.开启 Spring 自动配置（@EnableAutoConfiguration）：根据项目依赖自动配置 Spring 上下文（如数据源、Web 容器等）。
 * 2.扫描组件（@ComponentScan）：默认扫描当前类所在包（com.zjt.shortlink.admin）及其子包下的 @Controller、@Service、@Repository、@Component 等注解的类，将其注册为 Spring 容器的 Bean（如 UserController、UserServiceImpl 等）。
 * 3.标注配置类（@Configuration）：允许在类中定义 @Bean 方法注册额外组件。
 *
 * MapperScan: 注解指定 com.zjt.shortlink.admin.dao.mapper 包路径，MyBatis-Plus 会扫描该路径下的接口（如 UserMapper），自动生成实现类并注册为 Spring Bean，用于数据库操作。
 */

@SpringBootApplication
@MapperScan("com.zjt.shortlink.admin.dao.mapper")
public class ShortLinkAdminApplication {
    /**
     * main 方法启动Spring应用
     *  执行 SpringApplication.run(ShortLinkAdminApplication.class, args) 时，触发 Spring Boot 核心启动流程：
     *      1. 初始化 SpringApplication 实例
     *          1.1. 加载应用上下文（ApplicationContext），根据依赖判断应用类型（此处因引入 spring-boot-starter-web，判定为 Web 应用，默认启动嵌入式 Tomcat 容器）。
     *          1.2. 读取类路径下的配置文件（如 application.yaml），加载配置信息（如服务器端口 8002、数据源信息等）。
     *      2. 启动 Spring 容器（IoC 容器初始化）
     *          2.1. Bean 定义扫描与注册：根据 @ComponentScan 扫描路径，将 UserController（@RestController）、UserServiceImpl（@Service）等组件注册到容器中；根据 @MapperScan 注册 UserMapper 的代理实现类。
     *          2.2. 自动配置生效：Spring Boot 根据依赖自动配置核心组件：
     *                  1) Web 容器：启动 Tomcat 服务器，绑定端口 8002（由 server.port=8002 配置）。
     *                  2) 数据源：根据 spring.datasource 配置（MySQL 连接信息），通过 HikariCP 连接池初始化数据源，并绑定到 MyBatis-Plus 的 SQL 会话工厂。
     *                  3) Spring MVC：初始化 DispatcherServlet，处理 HTTP 请求映射（如 UserController 中的 /api/shortlink/v1/user/{username} 接口）。
     *      3. 应用就绪（Ready）
     *          3.1. 容器初始化完成后，Tomcat 服务器启动成功，应用开始监听端口 8002，对外提供 HTTP 服务。
     */
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAdminApplication.class, args);
    }
}
