package com.codetest.transactions.domain;

import java.util.List;

import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusInput;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;

public interface TransactionsService {
	String createTransaction(CreateTransactionInput createTransactionInput);

	List<TransactionInfo> searchTransactions(String accountIban, String sortMode);

	TransactionStatusOutput getTransactionStatus(TransactionStatusInput transactionStatusInput);
}
