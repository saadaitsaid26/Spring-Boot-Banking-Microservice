package com.saad.bankservice.dto;

import com.saad.bankservice.enums.AccountType;

public record BankAccountInput(
        double balance,
        String currency,
        AccountType type
) {}
