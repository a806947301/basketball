package com.wt.basketball;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wt.basketball.dao")
@SpringBootApplication
public class BasketballApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasketballApplication.class, args);
    }

}
