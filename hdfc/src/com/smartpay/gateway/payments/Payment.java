package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.models.Beneficiary;
import com.smartpay.gateway.models.Transaction;
public abstract class Payment {
	protected String id;
	protected double amount;
	protected Beneficiary beneficiary;
	
	public Payment(String id, double amount, Beneficiary beneficiary) {
		this.id = id;
		this.amount = amount;
		this.beneficiary = beneficiary;
		}
	public abstract Transaction processPayment() throws TransactionFailedException;
	public String getId() { return id; }
	}
