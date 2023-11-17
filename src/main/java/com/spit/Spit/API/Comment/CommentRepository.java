package com.spit.Spit.API.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query(name = "getAllCommentsByPostDesc-query", nativeQuery = true)
//    public List<Comment> getAllCommentsByPostDESC(Long postId);
}
