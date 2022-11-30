package com.codetest.transactions.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionInputDto {
	private String reference = ""; // Optional
	@JsonProperty("account_iban")
	private String accountIban = "";
	@JsonProperty("date")
	private String transactionDate = ""; // Optional
	private float amount = 0;
	private float fee = 0; // Optional
	private String description = ""; // Optional

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAccountIban() {
		return accountIban;
	}

	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
