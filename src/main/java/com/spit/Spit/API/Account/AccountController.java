package com.spit.Spit.API.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {

        if (account.getAccount_id() != null) {
            String message = "Remove account id when creating.";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        if (account.getName() == null || account.getHandle() == null ||
            account.getName().isEmpty() || account.getHandle().isEmpty()) {
            String message = "Name and handle cannot be null or empty";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        String message = accountServices.createAccount(account);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountServices.getAllAccounts();
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Account>> getAllAccounts() {
//        List<Account> accounts = accountServices.getAllAccounts();
//        return ResponseEntity.ok(accounts);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {

        Optional<Account> exist = accountServices.getAccountById(id);

        return exist.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity<Account> getAccountByHandle(@PathVariable String handle) {

        Optional<Account> exist = accountServices.getAccountByHandle(handle);

        return exist.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedAccountById(@PathVariable Long id) {

        Optional<Account> exist = accountServices.getAccountById(id);

        if(exist.isPresent()) {
            accountServices.deleteAccountById(id);
            String message = "Account with id " +id+ " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> fullyUpdateAccountById(@PathVariable Long id, @RequestBody EditAccountDTO updatedAccount) {

        Account exist = accountServices.getAccountById(id).orElse(null);

        if(exist != null) {
            if(!(updatedAccount.getName() == null || updatedAccount.getHandle() == null)) {
                accountServices.fullyUpdateAccountById(exist, updatedAccount);
                return new ResponseEntity<>("Account with id " +id+ " has been fully replaced.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Partial edits require a PATCH request.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partiallyEditAccountById(@PathVariable Long id, @RequestBody EditAccountDTO patchedAccount) {

        Account exist = accountServices.getAccountById(id).orElse(null);

        if(exist != null) {
            if(!(patchedAccount.getName() != null && patchedAccount.getHandle() != null)) {
                accountServices.partiallyEditAccountById(exist, patchedAccount);
                return new ResponseEntity<>("Account with id " +id+ " has been partially edited.", HttpStatus.OK);
            }
            return new ResponseEntity<>("An all-fields value change requires a PUT request.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
