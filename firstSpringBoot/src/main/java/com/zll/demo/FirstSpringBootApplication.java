package com.zll.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class FirstSpringBootApplication {

	public static void main(String[] args) {
		//SpringApplicationBuilder bulider = new SpringApplicationBuilder(App.class);
    	//bulider.bannerMode(Banner.Mode.OFF).run(args);
    	
    	//SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
    	//builder.application().setAdditionalProfiles("prod");
    	//builder.run(args);
    	/*  java -jar SpringBoot.jar --spring.profiles.active=prod --spring.config.name=App --spring.config.location=classPath:/  */
    	
    	SpringApplication.run(FirstSpringBootApplication.class, args);
        System.out.println( "################# start up success! #################" );
	}

}
