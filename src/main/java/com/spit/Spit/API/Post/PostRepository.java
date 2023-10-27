package com.spit.Spit.API.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(name = "getAllPostsDesc-query", nativeQuery = true)
    public List<GetPostDTO> getAllPostsDESC();


    /*
        SELECT a.handle, a.name, p.date, p.message FROM account AS a
        JOIN post AS p
        WHERE a.account_id = p.account_id
        AND
		a.handle = :handle
        ORDER BY p.date DESC;

    @Query(name = "getPostsByHandleDesc-query", nativeQuery = true)
    public List<GetPostDTO> getPostsByHandleDESC(String handle);

    */

    //public List<Post> getPostsByHashtagDESC(Long id);
}
