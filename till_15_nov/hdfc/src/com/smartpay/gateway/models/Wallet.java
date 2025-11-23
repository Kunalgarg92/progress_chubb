package com.smartpay.gateway.models;
public class Wallet {
	private String ownerId;
	private double balance;
	public Wallet(String ownerId, double balance) {
		this.ownerId = ownerId;
		this.balance = balance;
		}
	public double getBalance() {
		return balance;
		}
	public void credit(double amount) {
		balance += amount;
		}
	public void debit(double amount) {
		balance -= amount;
		}
	@Override
	public String toString() {
		return "Wallet{" + "ownerId='" + ownerId + '\'' + ", balance=" + balance + '}';
		}
	}
