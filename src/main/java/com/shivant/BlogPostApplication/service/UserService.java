package com.shivant.BlogPostApplication.service;

import com.shivant.BlogPostApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private com.shivant.BlogPostApplication.repository.userRepository userRepository;

    public User login(User user){
        User existingUser = userRepository.checkCredentials(user.getUsername(),user.getPassword());
        if(existingUser == null){
            return null;
        }
        else{
            return existingUser;
        }

    }
    public void registerUser(User newUser){
        userRepository.registerUser(newUser);
    }
}
