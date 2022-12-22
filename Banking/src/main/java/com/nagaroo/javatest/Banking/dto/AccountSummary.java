package com.nagaroo.javatest.Banking.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;


@Component
public class AccountSummary {
	
	Account account;
	List<Statement> statement;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Statement> getStatement() {
		return statement;
	}
	public void setStatement(List<Statement> statement) {
		this.statement = statement;
	}
	@Override
	public String toString() {
		return "AccountSummary [account=" + account + ", statement=" + statement + "]";
	}
	
	
	

}
