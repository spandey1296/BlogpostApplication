package com.shivant.BlogPostApplication.repository;

import com.shivant.BlogPostApplication.model.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class postRepository {
    public postRepository(){
        System.out.println("*** Post Repository ***");
    }

    @PersistenceUnit(unitName = "techblog")
    private  EntityManagerFactory entityManagerFactory;

    public List<Post> getAllPosts(Integer userId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p JOIN FETCH p.user puser WHERE puser.id = :userId",Post.class);
        query.setParameter("userId",userId);
        List<Post> result = query.getResultList();
        return result;
    }

    public void createPost(Post newPost) {
        //USING JPA
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(newPost);
            transaction.commit();

        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }

    }

    public Post getPost(Integer postId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Post.class,postId);
    }

    public void updatePost(Post updatedPost){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(updatedPost);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Post post = entityManager.find(Post.class,postId);
            entityManager.remove(post);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }
}
