package com.cs490.onlineshopping.dto;

public class PaymentOrderDTO {
	
	private String method;
	private String cardNumber;
	private String expiryDate;
	private String cvc;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public PaymentOrderDTO(String method, String cardNumber, String expiryDate, String cvc) {
		super();
		this.method = method;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvc = cvc;
	}

	public PaymentOrderDTO() {
		// TODO Auto-generated constructor stub
	}

}
