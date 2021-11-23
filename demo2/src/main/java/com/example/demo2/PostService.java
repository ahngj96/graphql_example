package com.example.demo2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class PostService {

	static public List<Post> PostDataBase;
	static public int lastId = 0;
	//{
	//	posts{
	//		id
	//  	title
	//	}
	//}

	@GraphQLQuery(name = "posts")
	public List<Post> getPosts(){
		return PostDataBase;
	}

	//{
	//	post(id:1){
	//		id
	//		title
	//	}
	//}
	@GraphQLQuery(name = "post")
	public Post getPostById(int idx){
		return PostDataBase.get(idx);
	}

	//{
	//	posts{
	//		title
	//		isGood
	//	}
	//}
	@GraphQLQuery(name = "isGood")
	// public boolean isGood(@GraphQLContext Post post) {
	public boolean isGood(Post post) {
		return !post.getTitle().equals("title1");
	}

	//mutation{
	//	savePost(post:{title:"title"}){
	//		id
	//		title
	//	}
	//}

	@GraphQLMutation(name = "savePost")
	public Post savePost(Post post) {
		if(post.getId() == null) {
			post.setId(new Long(lastId));
			lastId++;
		}
		PostDataBase.add(post);
		return post;
	}

	@GraphQLMutation(name = "updatePost")
	public Post updatePost(Post post) {
		PostDataBase.get(post.getId().intValue()).setTitle(post.getTitle());
		return PostDataBase.get(post.getId().intValue());
	}

	//mutation{
	//	deletePost(id:1)
	//}
	@GraphQLMutation(name = "deletePost")
	public void deletePost(Long id) {
		PostDataBase.remove(id);
	}



}
