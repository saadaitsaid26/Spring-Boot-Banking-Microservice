package com.saad.bankservice.service.impl;

import com.saad.bankservice.dto.AccountRequest;
import com.saad.bankservice.dto.AccountResponse;
import com.saad.bankservice.entity.BankAccount;
import com.saad.bankservice.mapper.BankAccountMapper;
import com.saad.bankservice.repository.BankAccountRepository;
import com.saad.bankservice.service.BankAccountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final BankAccountMapper mapper;

    public BankAccountServiceImpl(BankAccountRepository repository, BankAccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<AccountResponse> getAllAccounts() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public AccountResponse getAccountById(String id) {
        return mapper.toResponse(findEntity(id));
    }

    @Override
    public AccountResponse createBankAccount(AccountRequest request) {
        BankAccount account = mapper.toEntity(request);
        account.setId(UUID.randomUUID().toString());     // server controls the id
        account.setCreatedAt(new Date());             // and the timestamp
        return mapper.toResponse(repository.save(account));
    }

    @Override
    public AccountResponse updateBankAccount(String id, AccountRequest request) {
        BankAccount account = findEntity(id);
        if (request.balance() > 0) account.setBalance(request.balance());
        if (request.currency() != null) account.setCurrency(request.currency());
        if (request.type()     != null) account.setType(request.type());
        return mapper.toResponse(repository.save(account));
    }

    @Override
    public void deleteBankAccountById(String id) {
        repository.delete(findEntity(id));
    }

    // private helper: entity lookup lives in ONE place
    private BankAccount findEntity(String id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Bank Account with id: " + id + " not found"));
    }
}
