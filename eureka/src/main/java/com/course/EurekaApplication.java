package com.course;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaApplication {
//	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	private static String port2;

	@Value("${server.port}")
	public  String port;

	@PostConstruct
	public void getPort() {
		port2=this.port;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
		log.info("启动成功！！");
		log.info("Eureka地址: \thttp://127.0.0.1:"+port2);
	}


}
