package com.nagaroo.javatest.Banking.Repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.nagaroo.javatest.Banking.Entity.Account;
import com.nagaroo.javatest.Banking.Entity.Statement;
import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;



@Component
public class BankingRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	
	public Account getAccount(int id) throws SQLException,CustomException,Exception{
		String ACCOUNT_QUERY="select * from account where ID=?";
		 String ACCOUNT_Count1="select count(*) from account where ID="+id;
		 int row=jdbcTemplate.queryForObject(ACCOUNT_Count1,Integer.class);
		 if(row<1) {
				throw new CustomException("Please enter valid account id");
			}
			else {
			
				return jdbcTemplate.queryForObject(ACCOUNT_QUERY, BeanPropertyRowMapper.newInstance(Account.class),id);
			}
		

		
	}
	
	public List<Statement> getStatement1(int accountId) throws CustomException,Exception{
		 String STATEMENT_QUERY="select * from statement where account_id=?";
		String STATEMENT_Count="select count(*) from statement where account_id="+accountId;
		int row1=jdbcTemplate.queryForObject(STATEMENT_Count,Integer.class);
		
		if(row1<1) {
			throw new CustomException("no statement found for accountId " +accountId);
		}
		else {
		return jdbcTemplate.query(STATEMENT_QUERY, BeanPropertyRowMapper.newInstance(Statement.class),accountId);
		}
	}

}
