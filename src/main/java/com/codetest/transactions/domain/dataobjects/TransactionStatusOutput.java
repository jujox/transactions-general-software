package com.codetest.transactions.domain.dataobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TransactionStatusOutput {
	private String reference = "";
	private Status status = Status.UNDEFINED;
	@JsonInclude(Include.NON_NULL)
	private Float amount;
	@JsonInclude(Include.NON_NULL)
	private Float fee = null;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

}
