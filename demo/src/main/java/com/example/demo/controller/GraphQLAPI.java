package com.example.demo.controller;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;
import com.google.common.io.Resources;
import com.google.common.base.Charsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.demo.sample.CityDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Component
public class GraphQLAPI {

	@Autowired CityDataFetcher dataFetcher;

	private GraphQL graphQL;

	@Value("classpath:static/graphql/schema.graphqls")
	Resource resource;

	// (3)
	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	// (1)
	@PostConstruct
	public void init() throws IOException {
		URL url = resource.getURL();

		System.out.println(url);
		String sdl = Resources.toString(url, Charsets.UTF_8);
		System.out.println(sdl);
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	// (2)
	private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	private RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring()
			.type(
				TypeRuntimeWiring
					.newTypeWiring("Query")
					.dataFetcher("allCities", dataFetcher.allCities())
					.dataFetcher("city", dataFetcher.city())
			)
			.type(
				TypeRuntimeWiring
					.newTypeWiring("Mutation")
					.dataFetcher("putCity", dataFetcher.putCity())
			)
			.build();
	}

}
