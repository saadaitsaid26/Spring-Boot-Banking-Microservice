package com.saad.bankservice.repository;

import com.saad.bankservice.entity.BankAccount;
import com.saad.bankservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    @RestResource
    List<BankAccount> findBankAccountByType(@Param("t") AccountType accountType);

}
