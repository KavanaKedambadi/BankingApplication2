package com.nagaroo.javatest.Banking.Controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;
import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;
import com.nagaroo.javatest.Banking.Security.webSecurityConfiguration;
import com.nagaroo.javatest.Banking.Service.BankStatementService;
import com.nagaroo.javatest.Banking.Utility.DateFormater;
import com.nagaroo.javatest.Banking.dto.AccountInput;
import com.nagaroo.javatest.Banking.dto.AccountSummary;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
@ComponentScan(basePackages="com.nagaroo.javatest.Banking")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest
public class BankingCOntrollerMockMvcTest {
    @Autowired
	MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Mock
    BankStatementService bankStatementService;
    
    @Autowired
    DateFormater dateFormater;
    
   
    @InjectMocks
    BankingController bankingController;
    
    Account accountDetails;
    List<Statement> statementList;
    @Autowired 
    AccountSummary accountSummary;
    
   
    
    @BeforeEach
    public void setUp() {
    	mockMvc= MockMvcBuilders.
    			standaloneSetup(bankingController).build();
    	
    	
    }
    
    @Test
    public void test_getAccountStatement() throws ParseException, SQLException, CustomException, RuntimeException, Exception
    {
    	accountDetails =new Account(3,"current","0012250016003");
    	statementList = new ArrayList<Statement>();
    	statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
    	statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
    	accountSummary.setAccount(accountDetails);
    	accountSummary.setStatement(statementList);
    	Mockito.when(bankStatementService.getThreeMonthStatement(3)).thenReturn(accountSummary);
    	
    	this.mockMvc.perform(get("/statement/getAccountSummary"))
    	.andExpect(status().isOk())
    	.andDo(print());
    	
    }
    
    @Test
    public void test_getAccountStatement2() throws ParseException, SQLException, CustomException, RuntimeException, Exception
    {
    	AccountInput accountInput=new AccountInput(3,dateFormater.convertStringToDate("15.10.2012"),dateFormater.convertStringToDate("15.10.2012"),87.99,900);
    	
    	accountDetails =new Account(3,"current","0012250016003");
    	statementList = new ArrayList<Statement>();
    	statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
    	statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
    	accountSummary.setAccount(accountDetails);
    	accountSummary.setStatement(statementList);
    	Mockito.when(bankStatementService.getAccountStatement(accountInput)).thenReturn(accountSummary);
    	
    	this.mockMvc.perform(get("/statement/getAccountSummary"))
    	.andExpect(status().isOk())
    	.andDo(print());
    	
    }
    
   
}
