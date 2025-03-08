package com.Whispers.repository;

import com.Whispers.model.Like_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like_, Long> {

    Like_ findByAccountHandleAndPostId(String handle, Long id);
}
