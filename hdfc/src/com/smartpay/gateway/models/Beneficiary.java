package com.smartpay.gateway.models;

public class Beneficiary {
	private String id;
	private String name;
	private String accountOrUpi;
	public Beneficiary(String id, String name, String accountOrUpi) {
		this.id = id;
		this.name = name;
		this.accountOrUpi = accountOrUpi;
		}
	public String getId() { return id; }
	public String getName() { return name; }
	public String getAccountOrUpi() { return accountOrUpi; }
	@Override
	public String toString() {
		return "Beneficiary{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", accountOrUpi='" + accountOrUpi + '\'' + '}';
		}
	}
