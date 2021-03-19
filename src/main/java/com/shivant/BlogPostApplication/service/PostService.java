package com.shivant.BlogPostApplication.service;

import com.shivant.BlogPostApplication.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    //singleton
   // private static ArrayList<Post> POSTS = new ArrayList<>();

    //step 1:Create EntityManagerFactory

    @Autowired
    private com.shivant.BlogPostApplication.repository.postRepository postRepository;


    public List<Post> getAllPosts(Integer userId){
        return postRepository.getAllPosts(userId);
    }

    public void createPost(Post newPost){
        postRepository.createPost(newPost);
    }

    public Post getPost(Integer postId){
        return postRepository.getPost(postId);
    }

    public void updatePost(Post updatedPost) {
        postRepository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId){
        postRepository.deletePost(postId);
    }

}
