package com.Whispers.repository;

import com.Whispers.dto.GetCommentDTO;
import com.Whispers.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(name = "getAllCommentsByPostId-query", nativeQuery = true)
    List<GetCommentDTO> getAllCommentsByPostId(Long postId);

    @Query(name = "getAllCommentsByAccountId-query", nativeQuery = true)
    List<GetCommentDTO> getAllCommentsByAccountId(Long accountId);
}
