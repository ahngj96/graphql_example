package com.example.demo.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CityDataFetcher {

	public DataFetcher<?> allCities () {
		return environment -> {
			List<CityEntity> res = new ArrayList<>();
			for(int i = 0 ; i < 10; i++){
				res.add(CityEntity.builder().id(i).name("temp"+i).population(123*i).build());
			}
			return res;
		};
	}
	public DataFetcher<?> city () {
		return environment -> {
			int id = environment.getArgument("id");
			return CityEntity.builder().id(1).name("temp").population(123).build();
		};
	}
}
