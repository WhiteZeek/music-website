package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//配置类标明
@MapperScan("com.dao")//扫描dao
public class MusicApp {
    public static void main(String args[])
    {
        SpringApplication.run(MusicApp.class, args);
    }
}
