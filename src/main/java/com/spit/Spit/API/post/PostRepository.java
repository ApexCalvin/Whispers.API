package com.spit.Spit.API.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(name = "getAllPostsDesc-query", nativeQuery = true)
    public List<GetPostDTO> getAllPostsDesc();

    @Query(name = "getPostsByHandleDesc-query", nativeQuery = true)
    public List<GetPostDTO> getPostsByHandleDesc(String handle);

    //public List<Post> getPostsByHashtagDESC(Long id);
}
