package com.codetest.transactions.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsJpaRepository extends JpaRepository<TransactionsEntity, Integer> {

	List<TransactionsEntity> findAllByAccountIban(String accountIban);

	List<TransactionsEntity> findAllByAccountIbanOrderByAmountAsc(String accountIban);

	List<TransactionsEntity> findAllByAccountIbanOrderByAmountDesc(String accountIban);

	List<TransactionsEntity> findByReference(String reference);
}
