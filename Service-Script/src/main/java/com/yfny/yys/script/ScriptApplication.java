package com.yfny.yys.script;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.yfny.yys.script.mapper")
@ComponentScan(basePackages = {"com.yfny.yys.script.**","com.yfny.utilscommon"})
public class ScriptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScriptApplication.class, args);
    }

}
