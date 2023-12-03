package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.hashtag.Hashtag;
import com.spit.Spit.API.hashtag.HashtagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final AccountService accountService;

    private final HashtagService hashtagService;

    public PostService(PostRepository postRepository, AccountService accountService, HashtagService hashtagService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
        this.hashtagService = hashtagService;
    }

    @Transactional //multiple DB saves ensures entire method finished before persisting anything
    public void createPost(CreatePostDTO createPostDTO) {
        Post post = new Post();
        Account account = accountService.getAccountById(createPostDTO.getAccountId());
        post.setAccount(account);
        post.setMessage(createPostDTO.getMessage());
        if(!createPostDTO.getHashtags().isEmpty()){
            Set<Hashtag> hashtagsToSave = hashtagService.createHashtags(createPostDTO.getHashtags());
            post.setHashtags(hashtagsToSave);
        }

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

    public List<Post> getAllPostsByHashtagName(String hashtag) {
        return postRepository.findByHashtags_NameOrderByDateDesc(hashtag);
    }
}

