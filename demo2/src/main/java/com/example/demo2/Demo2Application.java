package com.example.demo2;

import java.util.ArrayList;
import java.util.List;
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
			Stream.of("title1", "title2", "title3", "title4", "title5", "title6")
				.forEach(title -> {
					List<Post> relatePosts_depth1 = new ArrayList<>();
					List<Post> relatePosts_depth2 = new ArrayList<>();
					List<Post> relatePosts_depth3 = new ArrayList<>();
					List<Post> relatePosts_depth4 = new ArrayList<>();
					List<Post> relatePosts_depth5 = new ArrayList<>();
					relatePosts_depth5.add(Post.builder().id(123L).title("related_depth5").build());
					relatePosts_depth4.add(
						Post.builder().id(123L).title("related_depth4").relatePosts(relatePosts_depth5).build());
					relatePosts_depth3.add(
						Post.builder().id(123L).title("related_depth3").relatePosts(relatePosts_depth4).build());
					relatePosts_depth2.add(
						Post.builder().id(123L).title("related_depth2").relatePosts(relatePosts_depth3).build());
					relatePosts_depth1.add(
						Post.builder().id(123L).title("related_depth1").relatePosts(relatePosts_depth2).build());

					Post post = Post.builder().title(title).relatePosts(relatePosts_depth1).build();
					postservice.savePost(post);
				});
			postservice.getPosts().forEach(System.out::println);
		};
	}
}
