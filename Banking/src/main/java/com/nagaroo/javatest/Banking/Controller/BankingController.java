package com.nagaroo.javatest.Banking.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagaroo.javatest.Banking.ExceptionHandler.CustomException;
import com.nagaroo.javatest.Banking.Service.BankStatementService;
import com.nagaroo.javatest.Banking.dto.AccountInput;
import com.nagaroo.javatest.Banking.dto.AccountSummary;



@Controller
public class BankingController implements ErrorController {
	
	@Autowired
	AccountSummary accountSummary;
	
	@Autowired
	BankStatementService bankingService;

	
	private static Logger logger=LoggerFactory.getLogger(BankingController.class);
	
	@GetMapping("/login")
    public String viewHomePage() {
        
        return "login";
    }
	
	@GetMapping("/statement/userInput")
	public String viewUserInput(Model model) {
		AccountInput accountInput=new AccountInput();
		model.addAttribute("accountInput", accountInput);
		
		return "userInput";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
	
	
	
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/statement/getAccountSummary")
	public ModelAndView  getAccountStatement(@ModelAttribute("accountInput") AccountInput accountInput ) throws CustomException,Exception {
		ModelAndView mav =new ModelAndView("statement");
		
		
		
			//accountSummary =bankingService.getAccountStatement(accountInput.getId());
			if((accountInput.getFromAmount()==0.00 && accountInput.getFromAmount()==0.00 && (accountInput.getFromDate()==null) && (accountInput.getToDate()==null)))
			{
				int id= accountInput.getId();
				AccountSummary summary;
				
					summary = bankingService.getThreeMonthStatement(id);
			
				
				mav.addObject("account", summary.getAccount());
				mav.addObject("Statement", summary.getStatement());
				
				
			}else  {
				
				AccountSummary summary =bankingService.getAccountStatement(accountInput);
				mav.addObject("account", summary.getAccount());
				mav.addObject("Statement", summary.getStatement());
			}
			
	
	

		
		return mav;
		}
		
		@ExceptionHandler(Exception.class)
	    public ModelAndView handleException(Exception ex) {
	        ModelAndView model = new ModelAndView("Exception");
	 
	        model.addObject("exception", ex.getMessage());
	        
	         
	        return model;
	    }
		@ExceptionHandler(CustomException.class)
	    public ModelAndView handleIOException(CustomException ex) {
	        ModelAndView model = new ModelAndView("CustomException");
	 
	        model.addObject("exception", ex.getErrorMessage());
	        
	         
	        return model;
	    }
		
		
//		
//@GetMapping("/error")
//public ModelAndView error(HttpServletRequest request,
//		HttpServletResponse response) {
//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	String currentPrincipalName = authentication.getName();
//	 ModelAndView model = new ModelAndView("error");
//	 model.addObject("error","user "+currentPrincipalName+" is not unauthorized");
//			return model;
//	
//
//}

	   


}
