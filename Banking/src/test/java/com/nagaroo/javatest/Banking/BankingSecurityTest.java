package com.nagaroo.javatest.Banking;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import com.nagaroo.javatest.Banking.Controller.BankingController;
@ComponentScan(basePackages="com.nagaroo.javatest.Banking")
@AutoConfigureMockMvc
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class BankingSecurityTest {
	@Autowired
	MockMvc mockMvc;
	 @Autowired
	 private WebApplicationContext context;
	 
	 @InjectMocks
	    BankingController bankingController;
	 @Before
	    public void setup() {
	    	mockMvc = MockMvcBuilders
	          .webAppContextSetup(context)
	          .apply(springSecurity())
	          .build();
	    }
	 
	 @Test
	 void test_getAccountstatemetMethodAccess() throws Exception {
		 
		 this.mockMvc.perform(get("/statement/getAccountSummary"))
	    	.andExpect(status().isOk())
	    	.andDo(print());
	    	
	 }

}
