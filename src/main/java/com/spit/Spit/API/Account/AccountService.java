package com.spit.Spit.API.Account;

import com.spit.Spit.API.Tools.DtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<String> createAccount(CreateAccountDTO newAccount) {
        Account account = DtoMapper.fromCreateAccountDTO(newAccount);

        try{
            accountRepository.save(account);
        }catch (Exception e){
            return new ResponseEntity<>("Account failed to save.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Account has been successfully saved.", HttpStatus.CREATED);
    }

    public List<Account> getAllAccounts() {
            return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account getAccountByHandle(String handle) {
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

    public boolean isHandleAvailable(String handle) {
        Account exist = getAccountByHandle(handle);
        return exist != null;
    }

//    public Account getExistingAccount(Long id) {
//        return getAccountById(id).orElse(null);
//    }

//    public boolean isHandleAvailable2(String handle) {
//        List<Account> accounts = getAllAccounts();
//        for (Account a : accounts) {
//            if (a.getHandle().equals(handle)) return false;
//        }
//        return true;
//    }
}