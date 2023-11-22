package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
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

    public void createPost(CreatePostDTO createPostDTO) {
        Post post = new Post();
        Account account = accountService.getAccountById(createPostDTO.getAccountId());
        post.setAccount(account);
        post.setMessage(createPostDTO.getMessage());
        postRepository.save(post);
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

    public List<GetPostDTO> getLikedPostsByAccountId(Long accountId) {
        return postRepository.getLikedPostsByAccountId(accountId);
    }


    //public List<Post> getPostByHashtagDESC(Long id) { return null; }
}

