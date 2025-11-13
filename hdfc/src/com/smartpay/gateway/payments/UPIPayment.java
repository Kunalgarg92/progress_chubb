package com.smartpay.gateway.payments;
import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.models.*;
import com.smartpay.gateway.utils.LoggerUtil;
import com.smartpay.gateway.interfaces.Retryable;
public class UPIPayment extends Payment implements Retryable {
	private String upiId;
	private String upiPinMasked;
	public UPIPayment(String id, double amount, Beneficiary beneficiary, String upiId, String upiPin) {
		super(id, amount, beneficiary);
		this.upiId = upiId;
		this.upiPinMasked = LoggerUtil.maskSensitive(upiPin);
		}
	@Override
	public Transaction processPayment() throws TransactionFailedException {
		Transaction tx = new Transaction(id, amount, "UPI");
		try {
			if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero");
			if ("0000".equals(upiPinMasked)) {
				throw new InvalidCredentialsException("UPI PIN invalid");
				}
			if (Math.random() < 0.08) throw new PaymentGatewayTimeoutException("UPI gateway timed out");
			tx.setResult(true, "Paid via UPI to " + beneficiary.getAccountOrUpi());
			LoggerUtil.logInfo("UPIPayment successful: " + tx);
			return tx;
			} catch (Exception e) {
				LoggerUtil.logError("UPIPayment failed: " + LoggerUtil.maskSensitive(e.getMessage()));
				throw new TransactionFailedException("UPI payment failed", e);
				}
		}
	@Override
	public boolean retry() throws Exception {
		LoggerUtil.logInfo("Retrying UPI payment " + id);
		Transaction tx = processPayment();
		return tx != null && tx.toString().contains("success");
		}
	}

