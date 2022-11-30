package com.codetest.transactions.domain;

import java.util.List;

import org.apache.commons.text.RandomStringGenerator;

import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusInput;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;
import com.codetest.transactions.domain.repository.TransactionsRepository;
import com.codetest.transactions.domain.status.GetTransactionStatus;
import com.codetest.transactions.domain.status.GetTransactionStatusFactory;
import com.codetest.transactions.infrastructure.repository.jpa.SortMode;

public class TransactionsServiceImp implements TransactionsService {

	TransactionsRepository transactionsRepository;

	public TransactionsServiceImp(TransactionsRepository translationsRepository) {
		this.transactionsRepository = translationsRepository;
	}

	@Override
	public String createTransaction(CreateTransactionInput createTransactionInput) {
		System.out.print(createTransactionInput.toString());
		if (createTransactionInput.getReference().isEmpty()) {
			createTransactionInput.setReference(createReference());
		}
		return transactionsRepository.saveTransaction(createTransactionInput);
	}

	@Override
	public List<TransactionInfo> searchTransactions(String accountIban, String sortMode) {
		SortMode sortModeRequest = getSortMode(sortMode);
		return transactionsRepository.searchTransactions(accountIban, sortModeRequest);
	}

	@Override
	public TransactionStatusOutput getTransactionStatus(TransactionStatusInput transactionStatusInput) {
		TransactionInfo transaction = transactionsRepository.getTransactionStatus(transactionStatusInput.reference);
		GetTransactionStatus getTransactionStatus = GetTransactionStatusFactory
				.getTransactionStatus(transactionStatusInput.channel);
		if (getTransactionStatus == null) {
			return null;
		}

		return getTransactionStatus.getStatus(transaction, transactionStatusInput.reference);
	}

	private SortMode getSortMode(String sortMode) {
		if (sortMode != null) {
			if (sortMode.equals("asc")) {
				return SortMode.ASC;
			} else {
				return SortMode.DESC; // default value if param is present
			}
		} else {
			return SortMode.NOSORT;
		}

	}

	private String createReference() {
		return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(10);
	}

}
