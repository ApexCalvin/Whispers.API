package com.spit.Spit.API.tools;


import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.account.CreateAccountDTO;

public class DtoMapper {

    private final AccountService accountService;

    public DtoMapper(AccountService accountService) {
        this.accountService = accountService;
    }

    public static Account fromCreateAccountDTO(CreateAccountDTO createAccountDTO){
        Account account = new Account();
        account.setName(createAccountDTO.getName());
        account.setHandle(createAccountDTO.getHandle());
        return account;
    }

//    public static Post fromCreatePostDTO(CreatePostDTO createPostDTO){
//        Post post = new Post();
//        post.setAccount(accountService.getAccountById(createPostDTO.getAccountId()));
//        post.setMessage(createPostDTO.getMessage());
//        return post;
//    }
}
