package com.shivant.BlogPostApplication.controller;

import com.shivant.BlogPostApplication.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getAllPost(Model model){
        ArrayList<Post> posts = new ArrayList<Post>();
        Post post1 = new Post();
        post1.setTitle("Smart Phone");
        post1.setBody("Body of smart phone");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle(" Phone");
        post2.setBody("Body of android");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("i Phone");
        post3.setBody("Body of iphone");
        post3.setDate(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        model.addAttribute("posts",posts);
        return "index";
    }
}
