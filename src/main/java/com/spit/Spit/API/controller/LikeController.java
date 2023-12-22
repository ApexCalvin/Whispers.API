package com.spit.Spit.API.controller;

import com.spit.Spit.API.dto.CreateLikeDTO;
import com.spit.Spit.API.service.LikeService;
import com.spit.Spit.API.model.Like_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<Like_> createLike(@RequestBody CreateLikeDTO likeDTO) {
        if(likeService.doesUserAlreadyLikePost(likeDTO)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else {
            Like_ createdLike = likeService.createLike(likeDTO);
            return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like_> getLikeById(@PathVariable Long id) {
        Like_ like = likeService.getLikeById(id);
        if (like != null) {
            return new ResponseEntity<>(like, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/handle/{handle}/post/{postId}")
    public ResponseEntity<Like_> getLikeByHandleAndPost(@PathVariable String handle, @PathVariable Long postId) {
        Like_ like = likeService.getLikeByHandleAndPost(handle, postId);
        if (like != null) {
            return new ResponseEntity<>(like, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Like_>> getAllLikes() {
        List<Like_> likes = likeService.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        boolean deleted = likeService.deleteLike(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

