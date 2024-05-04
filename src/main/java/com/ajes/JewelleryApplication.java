package com.ajes;

import com.ajes.utility.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@EntityScan(basePackages = "com.ajes.model")
@ComponentScan(basePackages = "com.ajes")
public class JewelleryApplication {
	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter();
	}
	public static void main(String[] args) {
        SpringApplication.run(JewelleryApplication.class, args);
//
//		LocalDate localDate = LocalDate.now();
////		Date date = java.sql.Date.valueOf(localDate);
//		System.out.println(localDate);

        LocalDate localDate = LocalDate.now();

    }

    }

