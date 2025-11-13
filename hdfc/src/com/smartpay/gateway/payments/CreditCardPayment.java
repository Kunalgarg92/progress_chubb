package com.smartpay.gateway.payments;
import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.models.*;
import com.smartpay.gateway.utils.LoggerUtil;
import com.smartpay.gateway.interfaces.Refundable;
public class CreditCardPayment extends Payment implements Refundable {
	private String cardNumberMasked;
	private String cardHolder;
	public CreditCardPayment(String id, double amount, Beneficiary beneficiary, String cardNumber, String cardHolder) {
		super(id, amount, beneficiary);
		this.cardNumberMasked = LoggerUtil.maskSensitive(cardNumber);
		this.cardHolder = cardHolder;
		}
	@Override
	public Transaction processPayment() throws TransactionFailedException {
		Transaction tx = new Transaction(id, amount, "CREDIT_CARD");
		try {
			if (amount <= 0) throw new InvalidAmountException("Amount must be positive");
			if (Math.random() < 0.05) throw new PaymentGatewayTimeoutException("Gateway timed out");
			tx.setResult(true, "Paid with card " + cardNumberMasked);
			LoggerUtil.logInfo("CreditCardPayment successful: " + tx);
			return tx;
			} catch (Exception e) {
				LoggerUtil.logError("CreditCardPayment failed: " + e.getMessage());
				throw new TransactionFailedException("Credit card payment failed", e);
				}
		}
	@Override
	public boolean refund(double amount) throws Exception {
		LoggerUtil.logInfo("Refunding " + amount + " to cardholder " + cardHolder);
		return true;
		}
	}