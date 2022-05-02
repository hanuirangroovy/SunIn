package com.ssafy.sunin;

import com.ssafy.sunin.config.properties.AppProperties;
import com.ssafy.sunin.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class SuninApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuninApplication.class, args);
	}

}