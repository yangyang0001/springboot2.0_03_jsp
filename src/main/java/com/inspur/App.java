package com.inspur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * User: YANG
 * Date: 2019/6/10-23:18
 * Description: No Description
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer { //外置tomcat 部署的时候 修改1

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 外置tomcat 部署的时候 修改2
	 *
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
}
