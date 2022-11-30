package com.codetest.transactions.application.dto;

public class TransactionStatusInputDto {
	private String reference = "";
	private String channel = "";

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
