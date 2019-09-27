package com.pezy.pezy_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.properties.FileStorageProperties;
import com.pezy.pezy_api.properties.SecuritiesProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class,
	SecuritiesProperties.class
})
public class PezyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PezyApiApplication.class, args);
	}


	@SuppressWarnings("deprecation")
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("*");
	        }
	    };
	}
	
}
