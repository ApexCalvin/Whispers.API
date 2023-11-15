package com.spit.Spit.API.Post;

import com.spit.Spit.API.Tools.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(CreatePostDTO post) {
        postRepository.save(DtoMapper.fromCreatePostDTO(post));
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public List<GetPostDTO> getAllPostsDesc() {
        return postRepository.getAllPostsDesc();
    }

    public List<GetPostDTO> getPostsByHandleDesc(String handle) {
        return postRepository.getPostsByHandleDesc(handle);
    }

    //public List<Post> getPostByHashtagDESC(Long id) { return null; }
}
