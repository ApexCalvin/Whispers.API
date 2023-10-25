package com.spit.Spit.API.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //public List<Comment> getAllCommentsByPostDESC(Long postId);
}
