package com.codetest.transactions.application.dto;

import com.codetest.transactions.domain.dataobjects.Channel;
import com.codetest.transactions.domain.dataobjects.CreateTransactionInput;
import com.codetest.transactions.domain.dataobjects.TransactionStatusInput;

public class TransactionServiceInputMapper {

	public static CreateTransactionInput mapCreateTransactionInput(
			CreateTransactionInputDto createTransactionInputDto) {
		CreateTransactionInput createTransactionInput = new CreateTransactionInput();
		createTransactionInput.setReference(createTransactionInputDto.getReference());
		createTransactionInput.setAccountIban(createTransactionInputDto.getAccountIban());
		createTransactionInput.setTransactionDate(createTransactionInputDto.getTransactionDate());
		createTransactionInput.setAmount(createTransactionInputDto.getAmount());
		createTransactionInput.setFee(createTransactionInputDto.getFee());
		createTransactionInput.setDescription(createTransactionInputDto.getDescription());
		return createTransactionInput;
	}

	public static TransactionStatusInput transationStatusDtoToTransactionStatus(
			TransactionStatusInputDto transactionStatusInputDto) {
		TransactionStatusInput transactionStatusInput = new TransactionStatusInput();
		transactionStatusInput.reference = transactionStatusInputDto.getReference();
		transactionStatusInput.channel = getChannel(transactionStatusInputDto.getChannel());
		return transactionStatusInput;
	}

	public static Channel getChannel(String channel) {
		if (channel == null) {
			return Channel.NOCHANNEL;
		}
		if (channel.toLowerCase().equals("client")) {
			return Channel.CLIENT;
		}
		if (channel.toLowerCase().equals("atm")) {
			return Channel.ATM;
		}
		if (channel.toLowerCase().equals("internal")) {
			return Channel.INTERNAL;
		}
		return Channel.NOCHANNEL;
	}
}
