package com.xiemj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
@SpringBootApplication
public class XiemjApplication {

	public static void main(String[] args) {

		SpringApplication.run(XiemjApplication.class, args);
	}
}

*/




@SpringBootApplication
public class XiemjApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XiemjApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(XiemjApplication.class, args);
	}
}

