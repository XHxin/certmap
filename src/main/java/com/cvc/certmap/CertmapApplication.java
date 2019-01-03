package com.cvc.certmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*@SpringBootApplication
public class CertmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertmapApplication.class, args);
	}
}*/


@SpringBootApplication
public class CertmapApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(CertmapApplication.class, args);
	}
}