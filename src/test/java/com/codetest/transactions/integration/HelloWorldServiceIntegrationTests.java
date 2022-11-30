package com.codetest.transactions.integration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codetest.transactions.domain.TransactionsService;
import com.codetest.transactions.domain.dataobjects.Channel;
import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.Status;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusInput;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;

@SpringBootTest
class HelloWorldServiceIntegrationTests {

	@Autowired
	private TransactionsService transactionsService;

	private String insertTransaction(String reference, float amount, float fee, String accountIban) {
		CreateTransactionInput createTransactionInput = new CreateTransactionInput();
		createTransactionInput.setReference(reference);
		createTransactionInput.setAmount(amount);
		createTransactionInput.setFee(fee);
		createTransactionInput.setAccountIban(accountIban);
		createTransactionInput.setTransactionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		String createResponse = transactionsService.createTransaction(createTransactionInput);
		return createResponse;
	}

	@Test
	void testTransactionIsCreated() {
		String createResponse = insertTransaction("REFERENCE1", 100, 2, "IBAN001");
		Assertions.assertEquals("REFERENCE1", createResponse);
	}

	@Test
	void testSearchAsc() {
		List<TransactionInfo> searchResult = transactionsService.searchTransactions("IBAN002", "asc");
		Assertions.assertEquals(2, searchResult.size());
		Assertions.assertEquals("TESTREF1", searchResult.get(0).reference);
		Assertions.assertEquals("TESTREF2", searchResult.get(1).reference);
	}

	@Test
	void testSearchDesc() {
		List<TransactionInfo> searchResult = transactionsService.searchTransactions("IBAN002", "desc");
		Assertions.assertEquals(2, searchResult.size());
		Assertions.assertEquals("TESTREF2", searchResult.get(0).reference);
		Assertions.assertEquals("TESTREF1", searchResult.get(1).reference);
	}

	@Test
	void testBusinessRuleA() {
		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.ATM;
		transactionStatusInput.reference = "NONEXISTENTREFERENT";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.INVALID, transaction.getStatus());
		Assertions.assertEquals("NONEXISTENTREFERENT", transaction.getReference());
	}

	@Test
	void testBusinessRuleBAtm() {
		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.ATM;
		transactionStatusInput.reference = "TESTREF1";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.SETTLED, transaction.getStatus());
		Assertions.assertEquals("TESTREF1", transaction.getReference());
		Assertions.assertEquals(9, transaction.getAmount());
	}

	@Test
	void testBusinessRuleBClient() {
		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.CLIENT;
		transactionStatusInput.reference = "TESTREF1";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.SETTLED, transaction.getStatus());
		Assertions.assertEquals("TESTREF1", transaction.getReference());
		Assertions.assertEquals(9, transaction.getAmount());
	}

	@Test
	void testBusinessRuleC() {
		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.INTERNAL;
		transactionStatusInput.reference = "TESTREF1";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.SETTLED, transaction.getStatus());
		Assertions.assertEquals("TESTREF1", transaction.getReference());
		Assertions.assertEquals(10, transaction.getAmount());
		Assertions.assertEquals(1, transaction.getFee());
	}

	@Test
	void testBusinessRuleDAtm() {
		insertTransaction("REFERENCE2", 100, 2, "IBAN001");

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.ATM;
		transactionStatusInput.reference = "REFERENCE2";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.PENDING, transaction.getStatus());
		Assertions.assertEquals("REFERENCE2", transaction.getReference());
		Assertions.assertEquals(98, transaction.getAmount());
	}

	@Test
	void testBusinessRuleDClient() {
		insertTransaction("REFERENCE3", 100, 2, "IBAN001");

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.CLIENT;
		transactionStatusInput.reference = "REFERENCE3";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.PENDING, transaction.getStatus());
		Assertions.assertEquals("REFERENCE3", transaction.getReference());
		Assertions.assertEquals(98, transaction.getAmount());
	}

	@Test
	void testBusinessRuleE() {
		insertTransaction("REFERENCE5", 100, 2, "IBAN001");

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.INTERNAL;
		transactionStatusInput.reference = "REFERENCE5";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.PENDING, transaction.getStatus());
		Assertions.assertEquals("REFERENCE5", transaction.getReference());
		Assertions.assertEquals(100, transaction.getAmount());
		Assertions.assertEquals(2, transaction.getFee());
	}

	@Test
	void testBusinessRuleF() {

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.CLIENT;
		transactionStatusInput.reference = "TESTREF2";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.FUTURE, transaction.getStatus());
		Assertions.assertEquals("TESTREF2", transaction.getReference());
		Assertions.assertEquals(18, transaction.getAmount());
	}

	@Test
	void testBusinessRuleG() {

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.ATM;
		transactionStatusInput.reference = "TESTREF2";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.PENDING, transaction.getStatus());
		Assertions.assertEquals("TESTREF2", transaction.getReference());
		Assertions.assertEquals(18, transaction.getAmount());
	}

	@Test
	void testBusinessRuleH() {

		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.channel = Channel.INTERNAL;
		transactionStatusInput.reference = "TESTREF2";
		TransactionStatusOutput transaction = transactionsService.getTransactionStatus(transactionStatusInput);

		Assertions.assertEquals(Status.FUTURE, transaction.getStatus());
		Assertions.assertEquals("TESTREF2", transaction.getReference());
		Assertions.assertEquals(20, transaction.getAmount());
		Assertions.assertEquals(2, transaction.getFee());
	}

}
