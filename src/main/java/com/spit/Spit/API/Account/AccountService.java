package com.spit.Spit.API.Account;

import com.spit.Spit.API.Tools.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(CreateAccountDTO newAccount) {
        accountRepository.save(DtoMapper.fromCreateAccountDTO(newAccount));
    }

    public List<Account> getAllAccounts() {
            return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account getAccountByHandle(String handle) {
        return accountRepository.findByHandle(handle);
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
        return getAccountByHandle(handle) == null;
    }
}