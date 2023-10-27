package com.spit.Spit.API.Account;


public class AccountMapper {

    private AccountMapper(){
    }

    public static Account fromCreateAccountDTO(CreateAccountDTO createAccountDTO){
        Account account = new Account();
        account.setName(createAccountDTO.getName());
        account.setHandle(createAccountDTO.getHandle());
        return account;
    }
}
