package com.saad.bankservice.service;

import com.saad.bankservice.dto.AccountRequest;
import com.saad.bankservice.dto.AccountResponse;

import java.util.List;

public interface BankAccountService {
    List<AccountResponse> getAllAccounts();
    AccountResponse getAccountById(String id);
    AccountResponse createBankAccount(AccountRequest request);
    AccountResponse updateBankAccount(String id, AccountRequest request);
    void deleteBankAccountById(String id);
}
