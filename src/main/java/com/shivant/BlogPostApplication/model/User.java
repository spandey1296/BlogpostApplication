package com.shivant.BlogPostApplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //relationships
    //user to userProfile relation(one to one)
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "profile_id")
    private UserProfile userProfile;

    //user to posts relation(one to many)
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    //getter and setter for relationship


    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    //getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
