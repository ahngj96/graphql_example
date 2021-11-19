package com.example.demo;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.sample.CityDataFetcher;
import com.example.demo.sample.CityEntity;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init() {
		return args -> {

			CityDataFetcher.CityEntityDatabase = new ArrayList<>();
			for(int i = 0 ; i < 10; i++){
				CityDataFetcher.CityEntityDatabase.add(CityEntity.builder().id(i).name("temp"+i).population(123*i).build());
			}
		};
	}
}
