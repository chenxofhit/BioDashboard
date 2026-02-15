package com.bio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * SpringBoot 主程序
 * 
 * @author chenx
 * @since 2019-12-16
 * 
 */

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bio.*.dao")
@SpringBootApplication
public class Application{
	
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = app.run(args);
        
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        
        System.out.println("==================> run at http://localhost:" + port + contextPath + "  <==================");
    }

}