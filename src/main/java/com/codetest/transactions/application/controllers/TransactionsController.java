package com.codetest.transactions.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codetest.transactions.application.dto.CreateTransactionInputDto;
import com.codetest.transactions.application.dto.TransactionServiceInputMapper;
import com.codetest.transactions.application.dto.TransactionStatusInputDto;
import com.codetest.transactions.domain.TransactionsService;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;

@RestController
@RequestMapping("/v1")
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;

	@GetMapping("/health")
	public String helloWorld(String name) {
		return "Hello, " + name;
	}

	@PostMapping("/create")
	public String createTransaction(@RequestBody CreateTransactionInputDto createTransactionInputDto) {
		return transactionsService
				.createTransaction(TransactionServiceInputMapper.mapCreateTransactionInput(createTransactionInputDto));
	}

	@GetMapping("/search")
	public List<TransactionInfo> searchTransactions(@RequestParam String accountIban,
			@RequestParam(required = false) String sortMode) {
		return transactionsService.searchTransactions(accountIban, sortMode);
	}

	@PostMapping("/status")
	public TransactionStatusOutput getStatus(@RequestBody TransactionStatusInputDto transactionStatusInputDto) {
		return transactionsService.getTransactionStatus(
				TransactionServiceInputMapper.transationStatusDtoToTransactionStatus(transactionStatusInputDto));
	}
}
