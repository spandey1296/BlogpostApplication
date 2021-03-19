package com.shivant.BlogPostApplication.controller;

import com.shivant.BlogPostApplication.model.Post;
import com.shivant.BlogPostApplication.model.User;
import com.shivant.BlogPostApplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    //IOC->INVERSION OF CONTROL | DEPENDENCY INJECTION
    @Autowired
    private PostService postService;

    @RequestMapping("/posts")
    public String getUserPost(Model model,HttpSession session){
        User user = (User) session.getAttribute("LoggedUser");
      //  PostService postService = new PostService();
        List<Post> posts = postService.getAllPosts(user.getId());
        model.addAttribute("posts",posts);
        return "posts";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/posts/newpost")
    public String newPost(){
        return "/posts/create";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/posts/create")
    public String createNewPost(Post newPost, HttpSession session){
        //pick the login user
        User user = (User)session.getAttribute("LoggedUser");
        newPost.setUser(user);

        //PostService postService = new PostService();
        newPost.setDate(new Date());
        postService.createPost(newPost);
        return "redirect:/posts";
    }

    //edit post
    @RequestMapping(method = RequestMethod.GET,value = "/editpost")
    public String editPost(@RequestParam(name = "postId")  Integer postId,Model model){
        Post post = postService.getPost(postId);
        model.addAttribute("post",post);
        return "posts/edit";
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/editpost")
    public String editPostSubmit(@RequestParam(name = "postId")  Integer postId,Post updatedPost,HttpSession session){
        updatedPost.setId(postId);
        User user = (User) session.getAttribute("LoggedUser");
        updatedPost.setUser(user);
        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/deletepost")
    public String deletePost(@RequestParam(name = "postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }



}
