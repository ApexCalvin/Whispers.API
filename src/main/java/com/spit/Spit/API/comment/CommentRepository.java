package com.spit.Spit.API.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(name = "getAllCommentsByPost-query", nativeQuery = true)
    public List<GetCommentDTO> getAllCommentsByPost(Long postId);
}
