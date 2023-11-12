package com.spit.Spit.API.Tools;


import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import com.spit.Spit.API.Post.CreatePostDTO;
import com.spit.Spit.API.Post.Post;

public class DtoMapper {

    static AccountService accountServices;

    private DtoMapper(){}

    public static Account fromCreateAccountDTO(CreateAccountDTO createAccountDTO){
        Account account = new Account();
        account.setName(createAccountDTO.getName());
        account.setHandle(createAccountDTO.getHandle());
        return account;
    }

    public static Post fromCreatePostDTO(CreatePostDTO createPostDTO){
        Post post = new Post();
        post.setAccount(accountServices.getAccountById(createPostDTO.getAccountId()));
        post.setMessage(createPostDTO.getMessage());
        return post;
    }
}
