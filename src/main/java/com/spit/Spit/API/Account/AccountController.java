package com.spit.Spit.API.Account;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountServices) {
        this.accountService = accountServices;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CreateAccountDTO account) {
        if(accountService.isHandleAvailable(account.getHandle())) {
            String message = accountService.createAccount(account);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Handle is not available.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> exist = accountService.getAccountById(id);
        return exist.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity<Account> getAccountByHandle(@PathVariable String handle) {
        Optional<Account> exist = accountService.getAccountByHandle(handle);
        return exist.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedAccountById(@PathVariable Long id) {

        Optional<Account> exist = accountService.getAccountById(id);

        if(exist.isPresent()) {
            accountService.deleteAccountById(id);
            String message = "Account with id " +id+ " has been deleted.";

            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> fullyUpdateAccountById(@PathVariable Long id, @Valid @RequestBody CreateAccountDTO updatedAccount) {
        Account exist = accountService.getExistingAccount(id);
        if(exist != null) {
                accountService.fullyUpdateAccountById(exist, updatedAccount);
                return new ResponseEntity<>("Account with id " +id+ " has been fully replaced.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partiallyEditAccountById(@PathVariable Long id, @RequestBody EditAccountDTO patchedAccount) {

        Account exist = accountService.getExistingAccount(id);

        if(exist != null) {
            if(!(patchedAccount.getName() != null && patchedAccount.getHandle() != null)) {
                accountService.partiallyEditAccountById(exist, patchedAccount);
                return new ResponseEntity<>("Account with id " +id+ " has been partially edited.", HttpStatus.OK);
            }
            return new ResponseEntity<>("An all-fields value change requires a PUT request.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
