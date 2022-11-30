package com.codetest.transactions.domain.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.codetest.transactions.domain.dataobjects.Status;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;

public interface GetTransactionStatus {
	TransactionStatusOutput getStatus(TransactionInfo transaction, String reference);

	public static TransactionStatusOutput invalidTransaction(String reference) {
		TransactionStatusOutput transactionStatusOutput = new TransactionStatusOutput();
		transactionStatusOutput.setStatus(Status.INVALID);
		transactionStatusOutput.setReference(reference);
		return transactionStatusOutput;
	}

	public static TransactionStatusOutput getTransaction(float amount, Status status, String reference) {
		TransactionStatusOutput transactionStatusOutput = new TransactionStatusOutput();
		transactionStatusOutput.setStatus(status);
		transactionStatusOutput.setReference(reference);
		transactionStatusOutput.setAmount(amount);
		return transactionStatusOutput;
	}

	public static TransactionStatusOutput getTransaction(float amount, float fee, Status status, String reference) {
		TransactionStatusOutput transactionStatusOutput = new TransactionStatusOutput();
		transactionStatusOutput.setStatus(status);
		transactionStatusOutput.setReference(reference);
		transactionStatusOutput.setAmount(amount);
		transactionStatusOutput.setFee(fee);
		return transactionStatusOutput;
	}

	public static Date getNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today;
		String todayAsString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		try {
			today = dateFormat.parse(todayAsString);
		} catch (ParseException e) {
			return null;
		}
		return today;
	}

	public static Date getTransactionDate(TransactionInfo transaction) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date transactionDate;
		try {
			transactionDate = dateFormat.parse(transaction.transactionDate.substring(0, 10));
		} catch (ParseException e) {
			return null;
		}
		return transactionDate;
	}
}
