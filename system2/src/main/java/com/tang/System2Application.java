package com.tang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class System2Application {
//	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	private static String port2;

	@Value("${server.port}")
	public  String port;

	@PostConstruct
	public void getPort() {
		port2=this.port;
	}

	public static void main(String[] args) {
		SpringApplication.run(System2Application.class, args);
		log.info("启动成功！！");
		log.info("System地址: \thttp://127.0.0.1:"+port2);
	}


}
