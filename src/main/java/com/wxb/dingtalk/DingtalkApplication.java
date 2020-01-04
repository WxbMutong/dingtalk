package com.wxb.dingtalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wxb.dingtalk.mapper")
public class DingtalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingtalkApplication.class, args);
		System.out.printf("=====================DingtalkApplication Start Success=====================");
	}

}
