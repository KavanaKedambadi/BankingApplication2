package com.nagaroo.javatest.Banking.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BankingRepositoryTest {
	@Autowired
	BankingRepository bankingRepository;
    
   @Autowired
    JdbcTemplate jdbcTemplate;
   
    @Test
   void testGetAccountDetails() throws SQLException, CustomException, Exception {

	   assertEquals(3,bankingRepository.getAccount(3).getId());
   }
    
    @Test
    void testGetStatementDetails() throws CustomException, Exception {
    	
    	assertEquals(31, bankingRepository.getStatement1(1).size());
    }

    @Test
    void testGetAccountDetailsException() throws SQLException, CustomException, Exception {

    	CustomException exception=assertThrows(CustomException.class,()->bankingRepository.getAccount(10));
        assertEquals("Please enter valid account id",exception.getErrorMessage());
    
       }
    
    @Test
    void testGetStatementDetailsException() throws CustomException, Exception {
    	
    	
    	CustomException exception=assertThrows(CustomException.class,()->bankingRepository.getStatement1(10));
        assertEquals("no statement found for accountId 10",exception.getErrorMessage());
    }

    
}
