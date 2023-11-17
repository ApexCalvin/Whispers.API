package com.spit.Spit.API.Post;

import com.spit.Spit.API.Account.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final AccountService accountService;

    public PostService(PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    public void createPost(CreatePostDTO post) {
        postRepository.save(DtoMapperfromCreatePostDTO(post));
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

    public Post DtoMapperfromCreatePostDTO(CreatePostDTO createPostDTO){
        Post post = new Post();
        post.setAccount(accountService.getAccountById(createPostDTO.getAccountId()));
        post.setMessage(createPostDTO.getMessage());
        return post;
    }
}

