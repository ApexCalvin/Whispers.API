package com.spit.Spit.API.repository;

import com.spit.Spit.API.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByHandle(String handle); // JPQL query method
}
