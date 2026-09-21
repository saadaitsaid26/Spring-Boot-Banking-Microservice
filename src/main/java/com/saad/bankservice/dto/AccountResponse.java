package com.saad.bankservice.dto;

import com.saad.bankservice.enums.AccountType;

import java.util.Date;

public record AccountResponse(
        String id,
        double balance,
        String currency,
        AccountType type,
        Date createdAt
) {}
