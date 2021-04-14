package com.course.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
@MapperScan("com.course.server.mapper")
public class BusinessApplication {
//	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	private static String port2;

	@Value("${server.port}")
	public  String port;

	@PostConstruct
	public void getPort() {
		port2=this.port;
	}

	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
		log.info("启动成功！！");
		log.info("Business地址: \thttp://127.0.0.1:"+port2+"/business/test");
	}


}
