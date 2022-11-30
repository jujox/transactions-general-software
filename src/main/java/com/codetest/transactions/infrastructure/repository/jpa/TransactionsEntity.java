package com.codetest.transactions.infrastructure.repository.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionsEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	@Column(name = "reference")
	public String reference = ""; // Optional
	@Column(name = "account_iban")
	public String accountIban = "";
	@Column(name = "transaction_date")
	public String transactionDate = ""; // Optional
	@Column(name = "amount")
	public float amount = 0;
	@Column(name = "fee")
	public float fee = 0; // Optional
	@Column(name = "description")
	public String description = ""; // Optional
}
