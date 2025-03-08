package com.Whispers.repository;

import com.Whispers.dto.GetPostDTO;
import com.Whispers.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(name = "getAllPostsDesc", nativeQuery = true)
    List<GetPostDTO> getAllPostsDesc();

    @Query(name = "getPostsByHandleDesc", nativeQuery = true)
    List<GetPostDTO> getPostsByHandleDesc(String handle);

    @Query(name = "getLikedPostsByAccountId", nativeQuery = true)
    List<GetPostDTO> getLikedPostsByAccountId(Long accountId);

    List<Post> findByHashtags_NameOrderByDateDesc(String hashtags_Name);
}
