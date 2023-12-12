package com.spit.Spit.API.tool;


import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.service.AccountService;
import com.spit.Spit.API.dto.CreateAccountDTO;

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

    public static CreateCommentDTO buildCreateCommentDTO(String message, Long postId, Long accountId) {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setMessage(message);
        createCommentDTO.setPostId(postId);
        createCommentDTO.setAccountId(accountId);
        return createCommentDTO;
    }
}
