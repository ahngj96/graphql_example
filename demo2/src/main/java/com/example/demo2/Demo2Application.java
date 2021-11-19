package com.example.demo2;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Bean
	ApplicationRunner init(PostService postservice) {
		return args -> {
			PostService.PostDataBase = new ArrayList<>();
			Stream.of("title1", "title2" , "title3", "title4", "title5", "title6")
				.forEach(title -> {
					Post post = Post.builder().title(title).build();
					postservice.savePost(post);
				});
			postservice.getPosts().forEach(System.out::println);
		};
	}
}
