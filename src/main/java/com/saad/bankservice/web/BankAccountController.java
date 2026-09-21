package com.saad.bankservice.web;

import com.saad.bankservice.dto.AccountRequest;
import com.saad.bankservice.dto.AccountResponse;
import com.saad.bankservice.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // GET /accounts
    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }

    // GET /accounts/{id}
    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable String id) {
        return bankAccountService.getAccountById(id);
    }

    // POST /accounts  -> 201 Created + Location header
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Validated AccountRequest request) {
        AccountResponse created = bankAccountService.createBankAccount(request);
        URI location = URI.create("/accounts/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // PUT /accounts/{id}
    @PutMapping("/{id}")
    public AccountResponse updateResponseEntityAccount(@PathVariable String id,
                                         @RequestBody @Validated AccountRequest request) {
        return bankAccountService.updateBankAccount(id, request);
    }

    // DELETE /accounts/{id}  -> 204 No Content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String id) {
        bankAccountService.deleteBankAccountById(id);
    }
}
