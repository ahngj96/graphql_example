package com.example.demo.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CityDataFetcher {
	static public List<CityEntity> CityEntityDatabase;

	public DataFetcher<?> allCities () {
		return environment -> {
			return CityEntityDatabase;
		};
	}
	public DataFetcher<?> city () {
		return environment -> {
			int id = environment.getArgument("id");
			return CityEntity.builder().id(1).name("temp").population(123).build();
		};
	}
	public DataFetcher<?> putCity () {
		return environment -> {
			int id = environment.getArgument("id");
			String name = environment.getArgument("name");
			CityEntity newCity = CityEntity.builder().id(id).name(name).build();
			CityEntityDatabase.add(newCity);
			return newCity;
		};
	}

}
