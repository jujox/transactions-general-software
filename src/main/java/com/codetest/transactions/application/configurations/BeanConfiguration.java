package com.codetest.transactions.application.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.codetest.transactions.TransactionsApplication;
import com.codetest.transactions.domain.TransactionsService;
import com.codetest.transactions.domain.TransactionsServiceImp;
import com.codetest.transactions.domain.repository.TransactionsRepository;

@Configuration
@ComponentScan(basePackageClasses = TransactionsApplication.class)
public class BeanConfiguration {

	@Bean
	TransactionsService getTransactionsService(final TransactionsRepository transactionsRepository) {
		return new TransactionsServiceImp(transactionsRepository);
	}

}
