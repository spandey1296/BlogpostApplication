package com.shivant.BlogPostApplication.controller;

import com.shivant.BlogPostApplication.model.User;
import com.shivant.BlogPostApplication.model.UserProfile;
import com.shivant.BlogPostApplication.service.PostService;
import com.shivant.BlogPostApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    //GET request to /users/login
    @RequestMapping(method = RequestMethod.GET,value = "/users/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "/users/login";
    }
    //POST request to /users/login
    @RequestMapping(method = RequestMethod.POST,value = "/users/login")
    public String loginUser(User user, HttpSession session){
        User exsitingUser = userService.login(user);
        //check if credentials are match
        if(exsitingUser == null){
            System.out.println("USER DOES NOT EXIST");
            return "/users/login";
        }else{
            //Maintain The session
            session.setAttribute("LoggedUser",exsitingUser);

            System.out.println("USER FOUND");
            return "redirect:/posts";
        }
    }
//for ui
    @RequestMapping(method = RequestMethod.GET,value = "/users/registration")
    public String registration(Model model){
        User user = new User();
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        model.addAttribute("user",user);

        return "/users/registration";
    }
   //for store the data
    @RequestMapping(method = RequestMethod.POST,value = "/users/registration")
    public String userRegistration(User user){
        userService.registerUser(user);

        return "redirect:/users/login";
    }

    @RequestMapping("/users/logout")
    public String userLogout(HttpSession session){
        //kill the session
        session.invalidate();
        return "redirect:/";
    }



}
