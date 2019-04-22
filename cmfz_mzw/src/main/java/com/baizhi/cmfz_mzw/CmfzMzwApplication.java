package com.baizhi.cmfz_mzw;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@MapperScan(basePackages = "com.baizhi.cmfz_mzw.dao")
public class CmfzMzwApplication {

    public static void main(String[] args) {

        SpringApplication.run(CmfzMzwApplication.class, args);
    }



}
