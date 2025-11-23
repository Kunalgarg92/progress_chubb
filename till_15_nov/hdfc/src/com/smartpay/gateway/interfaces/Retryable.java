package com.smartpay.gateway.interfaces;

public interface Retryable {
	boolean retry() throws Exception;
}
