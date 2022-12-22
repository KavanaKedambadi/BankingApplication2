package com.nagaroo.javatest.Banking;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversion;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;
import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;
import com.nagaroo.javatest.Banking.Repository.BankingRepository;
import com.nagaroo.javatest.Banking.Service.BankStatementService;
import com.nagaroo.javatest.Banking.Utility.DateFormater;
import com.nagaroo.javatest.Banking.Utility.NumberConversion;
import com.nagaroo.javatest.Banking.dto.AccountInput;
import com.nagaroo.javatest.Banking.dto.AccountSummary;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BankingServiceTest {
	

	
//	@Mock
//	JdbcTemplate jdbcTemplate;
    @Mock
	BankingRepository bankingRepository;

    @Autowired
	@InjectMocks
	BankStatementService bankStatementService;
	
	
	@Mock
	 DateFormater dateFormater;
	
	 @Mock
	 NumberConversion conversion;
	
	@Mock
	AccountInput accountInput;
	
	
	
	
	
	@Test
	@Order(1)
	void testGetStatementById() {
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
		
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();	
		Mockito.when(dateFormater.getThreeMonthDateFromCurrent()).thenCallRealMethod();
		
		
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			
assertEquals(2, bankStatementService.getThreeMonthStatement(3).getStatement().size());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Test
	void testGetStatementByUserInputDate() {
		
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"29.11.2018","736.64602102871"));
		
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();	
	
		AccountInput accountInput=new AccountInput();
		
		accountInput.setId(3);
		accountInput.setFromDate(dateFormater.convertStringToDate("15.10.2012"));
		accountInput.setToDate(dateFormater.convertStringToDate("30.11.2018"));
		System.out.println("inside date input test"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			assertEquals(2, bankStatementService.getAccountStatement(accountInput).getStatement().size());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(2)
	void testGetStatementByUserInputAmount() {
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
		AccountInput accountInput=new AccountInput();
		
		accountInput.setId(3);
		accountInput.setFromAmount(87.00);
		accountInput.setToAmount(700.00);
		Mockito.when(conversion.ConvertStringToDouble(anyString())).thenCallRealMethod();
			
		System.out.println("aacount input in testclass:"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			assertEquals(2, bankStatementService.getAccountStatement(accountInput).getStatement().size());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	@Order(3)
	void testGetStatementByUserInputAmountAnddate() {
		
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
		AccountInput accountInput=new AccountInput();
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();
		accountInput.setId(3);
		accountInput.setFromDate(dateFormater.convertStringToDate("15.10.2012"));
		accountInput.setToDate(dateFormater.convertStringToDate("30.11.2020"));
		accountInput.setFromAmount(87.00);
		accountInput.setToAmount(700.00);
		Mockito.when(conversion.ConvertStringToDouble(anyString())).thenCallRealMethod();
		
		System.out.println("aacount input in testclass test4:"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			assertEquals(1, bankStatementService.getAccountStatement(accountInput).getStatement().size());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(4)
	void testGetStatByIdException() {
		
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2012","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2012","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
			
		Mockito.when(dateFormater.getThreeMonthDateFromCurrent()).thenCallRealMethod();
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();
		try {
			Mockito.when(bankingRepository.getAccount(2)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(2)).thenReturn(statementList);
			Exception exception=assertThrows(RuntimeException.class,()->bankStatementService.getThreeMonthStatement(2));
            assertEquals("No statement found for this period",exception.getMessage());
           
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	@Order(5)
	void testGetStatementByUserInputDateException() {
		
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"29.11.2018","736.64602102871"));
		
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();	
	
		AccountInput accountInput=new AccountInput();
		
		accountInput.setId(3);
		accountInput.setFromDate(dateFormater.convertStringToDate("15.10.2019"));
		accountInput.setToDate(dateFormater.convertStringToDate("30.11.2019"));
		System.out.println("inside date input test"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			
			Exception exception=assertThrows(RuntimeException.class,()->bankStatementService.getAccountStatement(accountInput));
            assertEquals("No statement found for this period",exception.getMessage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}


	@Test
	@Order(6)
	void testGetStatementByUserInputAmountException() {
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
		AccountInput accountInput=new AccountInput();
		
		accountInput.setId(3);
		accountInput.setFromAmount(1000.00);
		accountInput.setToAmount(1010.00);
		Mockito.when(conversion.ConvertStringToDouble(anyString())).thenCallRealMethod();
			
		System.out.println("aacount input in testclass:"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			Exception exception=assertThrows(RuntimeException.class,()->bankStatementService.getAccountStatement(accountInput));
            assertEquals("No statement found for this period",exception.getMessage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(7)
	void testGetStatementByUserInputAmountAnddateException() {
		
		Account accountDetails =new Account(3,"current","0012250016003");
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Statement(51,3,"15.10.2012","744.591108244252"));
		statementList.add(new Statement(7,3,"15.11.2022","87.8901139771573"));
		statementList.add(new Statement(36,3,"15.11.2022","971.65314918067"));
		statementList.add(new Statement(113,3,"16.12.2012","602.336431872139"));
		statementList.add(new Statement(41,3,"30.11.2018","736.64602102871"));
		AccountInput accountInput=new AccountInput();
		Mockito.when(dateFormater.convertStringToDate(anyString())).thenCallRealMethod();
		accountInput.setId(3);
		accountInput.setFromDate(dateFormater.convertStringToDate("15.10.2012"));
		accountInput.setToDate(dateFormater.convertStringToDate("30.11.2020"));
		accountInput.setFromAmount(87.00);
		accountInput.setToAmount(100.00);
		Mockito.when(conversion.ConvertStringToDouble(anyString())).thenCallRealMethod();
		
		System.out.println("aacount input in testclass test4:"+accountInput);
		try {
			Mockito.when(bankingRepository.getAccount(3)).thenReturn(accountDetails);
			Mockito.when(bankingRepository.getStatement1(3)).thenReturn(statementList);
			Exception exception=assertThrows(RuntimeException.class,()->bankStatementService.getAccountStatement(accountInput));
            assertEquals("No statement found for this period",exception.getMessage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}
