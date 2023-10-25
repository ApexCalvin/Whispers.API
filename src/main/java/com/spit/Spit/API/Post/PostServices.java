package com.spit.Spit.API.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    @Autowired
    PostRepository postRepository;

    public String createPost(Post post) {
        post.setDate(new Date());
        postRepository.save(post);
        return "Post with id " +post.getId()+ " has been saved.";
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPostDESC() { //TODO: Create method
        return null;
    }

    public List<Post> getPostByHandleDESC(Long id) { //TODO: Create method
        return null;
    }

    //public List<Post> getPostByHashtagDESC(Long id) { return null; }

}
