package com.spit.Spit.API.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<_Like, Long> {
    _Like findByAccountHandleAndPostId(String handle, Long id);
}
