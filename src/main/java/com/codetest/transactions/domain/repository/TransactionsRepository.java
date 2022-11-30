package com.codetest.transactions.domain.repository;

import java.util.List;

import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.infrastructure.repository.jpa.SortMode;

public interface TransactionsRepository {
	String saveTransaction(CreateTransactionInput createTransactionInput);

	List<TransactionInfo> searchTransactions(String accountIban, SortMode sortMode);

	TransactionInfo getTransactionStatus(String reference);
}
