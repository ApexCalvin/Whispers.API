package com.spit.Spit.API.Like;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final AccountService accountService;

    @Autowired
    public LikeService(LikeRepository likeRepository, PostService postService, AccountService accountService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.accountService = accountService;
    }
    public PostLike createLike(CreateLikeDTO likeDTO) {
        PostLike postLike = new PostLike();
        Post post = postService.getPostById(likeDTO.getPostId());
        postLike.setPost(post);
        Account account = accountService.getAccountByHandle(likeDTO.getAccountHandle());
        postLike.setAccount(account);

        return likeRepository.save(postLike);
    }

    public PostLike getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public List<PostLike> getAllLikes() {
        return likeRepository.findAll();
    }

    public boolean deleteLike(Long id) {
        if(getLikeById(id) != null){
            likeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PostLike getLikeByHandleAndPost(String handle, Long postId) {
        return null;
    }
}
