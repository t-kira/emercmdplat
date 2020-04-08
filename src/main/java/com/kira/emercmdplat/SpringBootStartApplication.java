package com.kira.emercmdplat;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**

　　 新建一个SpringBootStartApplication 继承自 SpringBootServletInitializer

作用：因为在外部容器部署的话，就不能依赖于Application的main函数了，而是要以类似于web.xml文件配置的方式来启动Spring应用上下文，

　　　 此时我们需要在启动类中继承SpringBootServletInitializer并实现configure方法，这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似

【注意】：这个新建的类，与springboot的启动类是同级的
 */


public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("启动加载自定义的ServletInitializer");
        return application.sources(EmercmdplatApplication.class);
    }
}