package com.example.javaSpringCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JavaSpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringCrudApplication.class, args);
	}

	// cors configuration for client side data fetching
	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173/")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .maxAge(3600);
    }
}

}
