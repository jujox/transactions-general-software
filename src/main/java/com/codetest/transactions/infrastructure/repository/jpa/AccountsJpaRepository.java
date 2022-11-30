package com.codetest.transactions.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsJpaRepository extends JpaRepository<AccountsEntity, String> {

}
