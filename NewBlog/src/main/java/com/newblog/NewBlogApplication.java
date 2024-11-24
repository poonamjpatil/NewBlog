package com.newblog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NewBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewBlogApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}



}
