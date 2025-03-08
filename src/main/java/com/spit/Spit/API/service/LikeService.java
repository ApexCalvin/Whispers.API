package com.spit.Spit.API.service;

import com.spit.Spit.API.dto.CreateLikeDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.model.Like_;
import com.spit.Spit.API.repository.LikeRepository;
import com.spit.Spit.API.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Like_ createLike(CreateLikeDTO likeDTO) {
        Like_ like = new Like_();
        Post post = postService.getPostById(likeDTO.getPostId());
        like.setPost(post);
        Account account = accountService.getAccountByHandle(likeDTO.getAccountHandle());
        like.setAccount(account);

        return likeRepository.save(like);
    }

    public Like_ getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public List<Like_> getAllLikes() {
        return likeRepository.findAll();
    }

    public boolean deleteLike(Long id) {
        if(getLikeById(id) != null){
            likeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Like_ getLikeByHandleAndPost(String handle, Long postId) {
        return likeRepository.findByAccountHandleAndPostId(handle, postId);
    }
    public boolean doesUserAlreadyLikePost(CreateLikeDTO likeDTO) {
        return getLikeByHandleAndPost(likeDTO.getAccountHandle(), likeDTO.getPostId()) != null;
    }
}
