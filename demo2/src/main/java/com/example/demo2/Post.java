package com.example.demo2;

import java.util.List;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Post {
	@GraphQLQuery(name = "id")
	private Long id;
	@GraphQLQuery(name = "title")
	private String title;
	@GraphQLQuery(name = "relatePosts")
	private List<Post> relatePosts;
}
