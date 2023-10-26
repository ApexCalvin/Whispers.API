package com.spit.Spit.API.Account;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository;

    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String createAccount(CreateAccountDTO createAccountDTO) {

        Account account = new Account();
        account.setName(createAccountDTO.getName());
        account.setHandle(createAccountDTO.getHandle());
        accountRepository.save(account);

        return "Account with id " +account.getAccount_id()+ " has been saved.";
    }

    public List<Account> getAllAccounts() {

        Iterable<Account> all = accountRepository.findAll();
        List<Account> accountList = new ArrayList<>();
        all.forEach(accountList::add);
        return accountList;
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
}