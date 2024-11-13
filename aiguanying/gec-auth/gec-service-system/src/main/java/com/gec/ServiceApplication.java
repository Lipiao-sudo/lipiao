package com.gec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.gec.system.mapper")
public class ServiceApplication
{
    public static void main( String[] args ){
        SpringApplication.run(ServiceApplication.class, args);
    }
}
