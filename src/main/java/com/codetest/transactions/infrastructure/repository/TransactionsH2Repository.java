package com.codetest.transactions.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.repository.TransactionsRepository;
import com.codetest.transactions.infrastructure.repository.jpa.AccountsEntity;
import com.codetest.transactions.infrastructure.repository.jpa.AccountsJpaRepository;
import com.codetest.transactions.infrastructure.repository.jpa.SortMode;
import com.codetest.transactions.infrastructure.repository.jpa.TransactionsEntity;
import com.codetest.transactions.infrastructure.repository.jpa.TransactionsJpaRepository;

@Component
public class TransactionsH2Repository implements TransactionsRepository {

	private TransactionsJpaRepository transactionsJpaRepository;
	private AccountsJpaRepository accountsJpaRepository;

	public TransactionsH2Repository(TransactionsJpaRepository transactionsJpaRepository,
			AccountsJpaRepository accountsJpaRepository) {
		this.transactionsJpaRepository = transactionsJpaRepository;
		this.accountsJpaRepository = accountsJpaRepository;
	}

	@Override
	@Transactional
	public String saveTransaction(CreateTransactionInput createTransactionInput) {
		Optional<AccountsEntity> account = accountsJpaRepository.findById(createTransactionInput.getAccountIban());
		if (account.isEmpty()) {
			return "NOT FOUND"; // TODO TBD
		}
		if (account.get().balance < createTransactionInput.getAmount() + createTransactionInput.getFee()) {
			return "BALANCE IS NOT ENOUGH"; // TODO TBD
		}
		TransactionsEntity translationsEntityStoreResult = transactionsJpaRepository
				.save(TransactionsEntityMapper.transactionIntputDataToTransactionEntityMapper(createTransactionInput));

		// TODO: Update account balance
		return translationsEntityStoreResult.reference;
	}

	@Override
	public List<TransactionInfo> searchTransactions(String accountIban, SortMode sortMode) {
		List<TransactionInfo> transactions = new ArrayList<TransactionInfo>();
		List<TransactionsEntity> transactionsEntity = null;
		if (sortMode == SortMode.ASC) {
			transactionsEntity = transactionsJpaRepository.findAllByAccountIbanOrderByAmountAsc(accountIban);
		} else if (sortMode == SortMode.DESC) {
			transactionsEntity = transactionsJpaRepository.findAllByAccountIbanOrderByAmountDesc(accountIban);
		} else {
			transactionsEntity = transactionsJpaRepository.findAllByAccountIban(accountIban);
		}

		for (TransactionsEntity transaction : transactionsEntity) {
			transactions.add(TransactionsEntityMapper.transactionsEntityToTransactionInfoMapper(transaction));
		}
		return transactions;
	}

	@Override
	public TransactionInfo getTransactionStatus(String reference) {
		List<TransactionsEntity> transaction = transactionsJpaRepository.findByReference(reference);
		if (transaction.isEmpty()) {
			return null;
		}
		return TransactionsEntityMapper.transactionsEntityToTransactionInfoMapper(transaction.get(0));
	}

}
