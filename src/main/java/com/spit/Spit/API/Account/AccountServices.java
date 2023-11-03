package com.spit.Spit.API.Account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository;

    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String createAccount(CreateAccountDTO createAccountDTO) {
        Account account = AccountMapper.fromCreateAccountDTO(createAccountDTO);
        try{
            accountRepository.save(account);
        }catch (Exception e){
            return "Account failed to save";
        }
        return "Account successfully saved";
    }

    public List<Account> getAllAccounts() {
            return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByHandle(String handle) {
        return Optional.ofNullable(accountRepository.findByHandle(handle));
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public void fullyUpdateAccountById(Account currentAccount, CreateAccountDTO updatedAccount) {

        currentAccount.setName(updatedAccount.getName());
        currentAccount.setHandle(updatedAccount.getHandle());
        //keep List<Post>

        //replaces account if id is provided
        accountRepository.save(currentAccount);
    }

    public void partiallyEditAccountById(Account currentAccount, EditAccountDTO patchedAccount) {

        if(patchedAccount.getName() != null) {
            currentAccount.setName(patchedAccount.getName());
        }
        if(patchedAccount.getHandle() != null) {
            currentAccount.setHandle(patchedAccount.getHandle());
        }
        accountRepository.save(currentAccount);
    }

    public Account getExistingAccount(Long id) {
        return getAccountById(id).orElse(null);
    }

    public boolean isHandleAvailable2(String handle) {
        List<Account> accounts = getAllAccounts();

        for (Account a : accounts) {
            if (a.getHandle().equals(handle)) return false;
        }

        System.out.println("Test Commit from Mac");

        return true;
    }

    public boolean isHandleAvailable(String handle) {
        return getAccountByHandle(handle).isEmpty();
    }

}