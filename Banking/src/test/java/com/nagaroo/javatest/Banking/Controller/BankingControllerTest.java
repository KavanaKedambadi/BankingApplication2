package com.nagaroo.javatest.Banking.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;
import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;
import com.nagaroo.javatest.Banking.Service.BankStatementService;
import com.nagaroo.javatest.Banking.dto.AccountSummary;

@SpringBootTest
class BankingControllerTest {
	
	@Mock
	BankStatementService bankingService;
	
	@InjectMocks
	BankingController bankingController;
	
	Account account;
	List<Statement> statementList;
	
	@Autowired
	AccountSummary accountSummary;
     
@Test
void getAccountStatement() throws ParseException, SQLException, CustomException, RuntimeException, Exception {
	
	Account accountDetails =new Account(3,"current","0012250016003");
	List<Statement> statementList = new ArrayList<Statement>();
	
	statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
	statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));

	accountSummary.setAccount(accountDetails);
	accountSummary.setStatement(statementList);
	
	Mockito.when(bankingService.getThreeMonthStatement(3)).thenReturn(accountSummary);
	
	assertEquals(statementList.size(), accountSummary.getStatement().size());
	
	
	
}


}
