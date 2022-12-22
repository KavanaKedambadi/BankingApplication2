package com.nagaroo.javatest.Banking.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;
import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;
import com.nagaroo.javatest.Banking.Repository.BankingRepository;
import com.nagaroo.javatest.Banking.Utility.DateFormater;
import com.nagaroo.javatest.Banking.Utility.NumberConversion;
import com.nagaroo.javatest.Banking.dto.AccountInput;
import com.nagaroo.javatest.Banking.dto.AccountSummary;


import lombok.experimental.Accessors;

@Service
public class BankStatementService {
	
	
	@Autowired
	AccountSummary accountSummary;
	
	@Autowired
	BankingRepository bankingRepository;
	
	@Autowired
	DateFormater dateFormater;
	
	@Autowired
	NumberConversion conversion;
	
	private static Logger logger=LoggerFactory.getLogger(BankStatementService.class);
	
	
	public AccountSummary getThreeMonthStatement(int id) throws ParseException,SQLException, CustomException,RuntimeException,Exception {
		logger.info("fetching bank statement for 3 months from current date");
		//getting account details
		Account accountDetails = bankingRepository.getAccount(id);
		
		//getting statement details
		List<Statement> statementList=bankingRepository.getStatement1(id);
	    //System.out.println(statementList);
		System.out.println(accountDetails);
		System.out.println(statementList);
		//getting date which is 3 month before from now
		LocalDate threeMonthDateFromNow= dateFormater.getThreeMonthDateFromCurrent();
		//getting current date
		LocalDate currentDate=LocalDate.now();
		
		
		//new list containing 3 months  account details
		List<Statement> newstatementList=statementList.stream().filter(sl->
			dateFormater.convertStringToDate(sl.getDateField()).isBefore(currentDate)).filter(sl->dateFormater.convertStringToDate(sl.getDateField()).isAfter(threeMonthDateFromNow))
		.collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
            if (result.isEmpty()) throw new RuntimeException("No statement found for this period");
            return result;
        }));
		
		System.out.println("newlist:"+newstatementList);
		
        accountSummary.setAccount(accountDetails);
        accountSummary.setStatement(newstatementList);
        
        
		return accountSummary;
		
		
	}
	
	
	
	public AccountSummary getAccountStatement(AccountInput input) throws SQLException, CustomException, Exception {
		
		
		if(!((input.getFromDate()==null) && (input.getToDate()==null))) {
			logger.info("getting the bank statement based on the date range");
			System.out.println("getting the bank statement based on the date range");
			//getting account details
			Account accountDetails=bankingRepository.getAccount(input.getId());
			//getting statement details
			List<Statement> statementList=bankingRepository.getStatement1(input.getId());
			
			System.out.println(input);
			System.out.println(statementList);
			if((input.getFromAmount()<=0.0) && (input.getFromAmount()<=0.0)) {
				logger.info("get the statement based on date only");
					System.out.println("get the statement based on date only");			
				List<Statement> newstatementList=statementList.stream().filter(sl->
				dateFormater.convertStringToDate(sl.getDateField()).isBefore(input.getToDate())).filter(sl->dateFormater.convertStringToDate(sl.getDateField()).isAfter(input.getFromDate()))
			.collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
	            if (result.isEmpty()) throw new RuntimeException("No statement found for this period");
	            return result;
	        }));
				accountSummary.setStatement(newstatementList);
				System.out.println("newlist:"+newstatementList);		
			}else {
				System.out.println("date and amount filtering \n"+statementList);
				
				List<Statement> newstatementList=statementList.stream().filter(sl->
				dateFormater.convertStringToDate(sl.getDateField()).isBefore(input.getToDate())).filter(sl->dateFormater.convertStringToDate(sl.getDateField()).isAfter(input.getFromDate()))
			    .filter(sl-> conversion.ConvertStringToDouble(sl.getAmount())>=input.getFromAmount())
			    .filter(sl-> conversion.ConvertStringToDouble(sl.getAmount())<=input.getToAmount())
				.collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
	            if (result.isEmpty()) throw new RuntimeException("No statement found for this period");
	            return result;
	        }));
				accountSummary.setStatement(newstatementList);
				System.out.println("newlist:"+newstatementList);
				
			}
			
			accountSummary.setAccount(accountDetails);
	        
		}else {
			
			logger.info("getting the bank statement based on the amount range");
			System.out.println("getting the bank statement based on the amount range");
			//getting account details
			Account accountDetails=bankingRepository.getAccount(input.getId());
			//getting statement details
			List<Statement> statementList=bankingRepository.getStatement1(input.getId());
			System.out.println(input);
			System.out.println(statementList);
			List<Statement> newstatementList=statementList.stream()
					.filter(sl-> conversion.ConvertStringToDouble(sl.getAmount())>=input.getFromAmount())
				    .filter(sl-> conversion.ConvertStringToDouble(sl.getAmount())<=input.getToAmount())
					.collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
		            if (result.isEmpty()) throw new RuntimeException("No statement found for this period");
		            return result;
		        }));
			System.out.println("newlist:"+newstatementList);
			
			accountSummary.setAccount(accountDetails);
	        accountSummary.setStatement(newstatementList);
			
		}
	 
		return accountSummary;
		
	}

}
