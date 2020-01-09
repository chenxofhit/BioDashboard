package com.bio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bio.common.utils.SpringContextHolder;

/**
 * 
 * SpringBoot 主程序
 * 
 * @author chenx
 * @since 2019-12-16
 * 
 */

//@EnableTransactionManagement
//@ServletComponentScan
//@MapperScan("com.bio.*.dao")
//@SpringBootApplication
public class ApplicationEmbedded{
	
    public static void main(String[] args) {
    	
        SpringApplication.run(ApplicationEmbedded.class, args);
        ServerProperties serverProperties = SpringContextHolder.getApplicationContext().getBean(ServerProperties.class);
        
        System.out.println("==================> run at http://localhost:" + serverProperties.getPort() + serverProperties.getContextPath() + "  <==================");
    
    }

}