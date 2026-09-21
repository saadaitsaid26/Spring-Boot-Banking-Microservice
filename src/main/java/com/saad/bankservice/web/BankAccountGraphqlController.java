package com.saad.bankservice.web;

import com.saad.bankservice.dto.AccountRequest;
import com.saad.bankservice.dto.AccountResponse;
import com.saad.bankservice.service.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphqlController {

    private final BankAccountService bankAccountService;

    public BankAccountGraphqlController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @QueryMapping
    public List<AccountResponse> accountsList() {
        return bankAccountService.getAllAccounts();
    }

    @QueryMapping
    public AccountResponse accountById(@Argument String id) {
        return bankAccountService.getAccountById(id);
    }

    @MutationMapping
    public AccountResponse saveAccount(@Argument AccountRequest account) {
        return bankAccountService.createBankAccount(account);
    }

    @MutationMapping
    public AccountResponse updateAccount(@Argument String id, @Argument AccountRequest account) {
        return bankAccountService.updateBankAccount(id, account);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        bankAccountService.deleteBankAccountById(id);
        return true;
    }
}
