package com.spit.Spit.API.repository;

import com.spit.Spit.API.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    public Hashtag findByName(String name); //JPQL
}
