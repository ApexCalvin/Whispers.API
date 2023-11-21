package com.spit.Spit.API.like;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostService;
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
    public _Like createLike(CreateLikeDTO likeDTO) {
        _Like like = new _Like();
        Post post = postService.getPostById(likeDTO.getPostId());
        like.setPost(post);
        Account account = accountService.getAccountByHandle(likeDTO.getAccountHandle());
        like.setAccount(account);

        return likeRepository.save(like);
    }

    public _Like getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public List<_Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public boolean deleteLike(Long id) {
        if(getLikeById(id) != null){
            likeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public _Like getLikeByHandleAndPost(String handle, Long postId) {
        return likeRepository.findByAccountHandleAndPostId(handle, postId);
    }
    public boolean doesUserAlreadyLikePost(CreateLikeDTO likeDTO) {
        return getLikeByHandleAndPost(likeDTO.getAccountHandle(), likeDTO.getPostId()) != null;
    }
}
