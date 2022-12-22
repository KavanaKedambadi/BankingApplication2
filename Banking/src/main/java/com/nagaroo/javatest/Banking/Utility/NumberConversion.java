package com.nagaroo.javatest.Banking.Utility;

import org.springframework.stereotype.Component;

@Component
public class NumberConversion {
	
	public double ConvertStringToDouble(String amount) {
		double d = Double.valueOf(Double. parseDouble(amount));
		return d;
		 
	}

}
