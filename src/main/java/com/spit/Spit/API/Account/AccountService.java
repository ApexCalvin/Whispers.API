package com.spit.Spit.API.Account;

import com.spit.Spit.API.Tools.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    public static final String ACCOUNT_SAVED = "Account has been successfully saved.";
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(CreateAccountDTO newAccount) {
        Account account = DtoMapper.fromCreateAccountDTO(newAccount);
        accountRepository.save(account);
        return account;
    }

    public List<Account> getAllAccounts() {
            return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account getAccountByHandle(String handle) { //TODO: test "null response" on postman
        return accountRepository.findByHandle(handle);
        //return Optional.ofNullable(accountRepository.findByHandle(handle));
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public void putAccountById(Account currentAccount, CreateAccountDTO updatedAccount) {
        currentAccount.setName(updatedAccount.getName());
        currentAccount.setHandle(updatedAccount.getHandle());
        accountRepository.save(currentAccount); //replaces account if id is provided, else creates new account
    }

    public void patchAccountById(Account currentAccount, UpdateAccountDTO updatedAccount) {
        if(updatedAccount.getName() != null) currentAccount.setName(updatedAccount.getName());
        if(updatedAccount.getHandle() != null) currentAccount.setHandle(updatedAccount.getHandle());
        accountRepository.save(currentAccount);
    }

    public Boolean isHandleAvailable(String handle) {
        Account exist = getAccountByHandle(handle);
        return exist != null;
    }
}