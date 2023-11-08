package com.spit.Spit.API.Post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    private final PostRepository postRepository;

    public PostServices(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public String createPost(Post post) {
        //post.setDate(new Date());
        postRepository.save(post);
        return "Post with id " +post.getId()+ " has been saved.";
        //return "Post successfully saved.";
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

    public List<GetPostDTO> getAllPostDESC() {
        return postRepository.getAllPostsDESC();
    }

    public List<GetPostDTO> getPostsByHandleDESC(String handle) {
        return postRepository.getPostsByHandleDESC(handle);
    }

    //public List<Post> getPostByHashtagDESC(Long id) { return null; }

}
