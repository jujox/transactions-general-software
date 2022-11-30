package com.codetest.transactions.domain.status;

import java.util.Date;

import com.codetest.transactions.domain.dataobjects.Status;
import com.codetest.transactions.domain.dataobjects.TransactionInfo;
import com.codetest.transactions.domain.dataobjects.TransactionStatusOutput;

public class GetTransactionStatusForInternal implements GetTransactionStatus {

	@Override
	public TransactionStatusOutput getStatus(TransactionInfo transaction, String reference) {
		if (transaction == null) {
			return GetTransactionStatus.invalidTransaction(reference);
		}

		Date transactionDate = GetTransactionStatus.getTransactionDate(transaction);
		Date today = GetTransactionStatus.getNow();

		if (transactionDate.before(today)) {
			return GetTransactionStatus.getTransaction(transaction.amount, transaction.fee, Status.SETTLED, reference);
		} else if (transactionDate.equals(today)) {
			return GetTransactionStatus.getTransaction(transaction.amount, transaction.fee, Status.PENDING, reference);
		} else if (transactionDate.after(today)) {
			return GetTransactionStatus.getTransaction(transaction.amount, transaction.fee, Status.FUTURE, reference);
		}

		TransactionStatusOutput transactionStatusOutput = new TransactionStatusOutput();
		transactionStatusOutput.setAmount(transaction.amount);
		transactionStatusOutput.setFee(transaction.fee);
		transactionStatusOutput.setReference(transaction.reference);
		transactionStatusOutput.setStatus(Status.UNDEFINED);
		return transactionStatusOutput;
	}

}
