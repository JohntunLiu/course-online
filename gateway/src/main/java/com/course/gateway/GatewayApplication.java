package com.course.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class GatewayApplication {
//	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	private static String port2;

	@Value("${server.port}")
	public  String port;

	@PostConstruct
	public void getPort() {
		port2=this.port;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		log.info("启动成功！！");
		log.info("gateway地址: \thttp://127.0.0.1:"+port2);
	}


	/**
	 * 配置跨域
	 * @return
	 */
	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(Boolean.TRUE);
		config.addAllowedMethod("*");
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}


}
