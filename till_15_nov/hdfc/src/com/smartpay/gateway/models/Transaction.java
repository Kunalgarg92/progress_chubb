package com.smartpay.gateway.models;
import java.time.LocalDateTime;
public class Transaction {
	private String id;
	private double amount;
	private String type;
	private LocalDateTime timestamp;
	private boolean success;
	private String message;
	public Transaction(String id, double amount, String type) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.timestamp = LocalDateTime.now();
		}
	public void setResult(boolean success, String message) {
		this.success = success;
		this.message = message;
		}
	@Override
	public String toString() {
		return "Transaction{" + "id='" + id + '\'' + ", amount=" + amount + ", type='" + type + '\'' + ", timestamp=" + timestamp + ", success=" + success + ", message='" + message + '\'' + '}';
		}
	}
