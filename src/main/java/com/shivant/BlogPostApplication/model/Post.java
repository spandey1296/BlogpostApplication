package com.shivant.BlogPostApplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//pojo->plain old java object
@Entity
@Table(name = "posts")
public class Post {
    //components of the post
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "date")
    private Date date;

    //relationship
    //posts to user(many to one)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //relationship between posts and category(Many to Many)
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();



    //getter and setter for relation

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//getter and setter for

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
