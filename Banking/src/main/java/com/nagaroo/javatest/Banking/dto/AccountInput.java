package com.nagaroo.javatest.Banking.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class AccountInput {

	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	private double fromAmount;
	private double toAmount;
	
	
	
	public AccountInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountInput(int id, LocalDate fromDate, LocalDate toDate, double fromAmount, double toAmount) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromAmount = fromAmount;
		this.toAmount = toAmount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public double getToAmount() {
		return toAmount;
	}
	public void setToAmount(double toAmount) {
		this.toAmount = toAmount;
	}
	
	@Override
	public String toString() {
		return "userInput [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromAmount=" + fromAmount
				+ ", toAmount=" + toAmount + "]";
	}
	
	
}
