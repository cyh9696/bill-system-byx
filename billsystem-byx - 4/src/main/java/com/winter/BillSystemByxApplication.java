package com.winter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.winter.mapper")//将项目中的mapper路径加了进来


public class BillSystemByxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillSystemByxApplication.class, args);
    }

}
