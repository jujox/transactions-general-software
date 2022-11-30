package com.codetest.transactions.infrastructure.repository.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountsEntity {
	@Id
	@Column(name = "account_iban")
	public String accountIban = "";
	@Column(name = "balance")
	public float balance = 0;

}
