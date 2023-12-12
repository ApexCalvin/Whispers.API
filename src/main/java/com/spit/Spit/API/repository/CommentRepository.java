package com.spit.Spit.API.repository;

import com.spit.Spit.API.dto.GetCommentDTO;
import com.spit.Spit.API.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(name = "getAllCommentsByPostId-query", nativeQuery = true)
    public List<GetCommentDTO> getAllCommentsByPostId(Long postId);

    @Query(name = "getAllCommentsByAccountId-query", nativeQuery = true)
    public List<GetCommentDTO> getAllCommentsByAccountId(Long accountId);
}
