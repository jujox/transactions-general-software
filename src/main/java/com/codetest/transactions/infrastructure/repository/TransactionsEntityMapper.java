package com.codetest.transactions.infrastructure.repository;

import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.infrastructure.repository.jpa.TransactionsEntity;

public class TransactionsEntityMapper {

	public static TransactionsEntity transactionIntputDataToTransactionEntityMapper(
			CreateTransactionInput createTransactionInput) {
		TransactionsEntity transactionsEntity = new TransactionsEntity();
		transactionsEntity.reference = createTransactionInput.getReference();
		transactionsEntity.accountIban = createTransactionInput.getAccountIban();
		transactionsEntity.transactionDate = createTransactionInput.getTransactionDate();
		transactionsEntity.amount = createTransactionInput.getAmount();
		transactionsEntity.fee = createTransactionInput.getFee();
		transactionsEntity.description = createTransactionInput.getDescription();

		return transactionsEntity;

	}

	public static TransactionInfo transactionsEntityToTransactionInfoMapper(TransactionsEntity transactionsEntity) {
		TransactionInfo transactionInfo = new TransactionInfo();
		transactionInfo.accountIban = transactionsEntity.accountIban;
		transactionInfo.reference = transactionsEntity.reference;
		transactionInfo.transactionDate = transactionsEntity.transactionDate;
		transactionInfo.amount = transactionsEntity.amount;
		transactionInfo.fee = transactionsEntity.fee;
		transactionInfo.description = transactionsEntity.description;
		return transactionInfo;
	}
}
