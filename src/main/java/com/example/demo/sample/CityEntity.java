package com.example.demo.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityEntity {
	private int id;
	private String name;
	private int population;
}
