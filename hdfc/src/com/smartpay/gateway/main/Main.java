package com.smartpay.gateway.main;
import com.smartpay.gateway.payments.*;
import com.smartpay.gateway.models.*;
import com.smartpay.gateway.interfaces.*;
import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.utils.LoggerUtil;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.logInfo("Starting SmartPayment Gateway Simulator");
        Beneficiary[] benes = new Beneficiary[] {
                new Beneficiary("b1", "Alice", "alice@upi"),
                new Beneficiary("b2", "Bob", "bob@upi")
        };
        try {
            Beneficiary bene = findBeneficiary(benes, "b3"); 
        } catch (BeneNotFoundException e) {
            LoggerUtil.logWarn("Beneficiary lookup failed: " + e.getMessage());
        }
        Beneficiary target = benes[0];
        UPIPayment upiInvalid = new UPIPayment("tx-upi-1", -500, target, target.getAccountOrUpi(), "1234");
        try {
            upiInvalid.processPayment();
        } catch (TransactionFailedException e) {
            LoggerUtil.logError("Handled invalid amount for UPI: " + e.getCause().getMessage());
            LoggerUtil.logInfo("User-friendly: The amount entered is invalid. Please enter a positive amount.");
        }
        Wallet wallet = new Wallet("user1", 100.0);
        WalletPayment walletPayment = new WalletPayment("tx-w-1", 250.0, target, wallet);
        try {
            walletPayment.processPayment();
        } catch (TransactionFailedException e) {
            Throwable cause = e.getCause();
            if (cause instanceof InsufficientBalanceException) {
                LoggerUtil.logWarn("Insufficient funds: " + cause.getMessage());
                LoggerUtil.logInfo("Please recharge your wallet or choose another payment method.");
            } else {
                LoggerUtil.logError("Wallet payment error: " + e.getMessage());
            }
        }
        UPIPayment upiBadPin = new UPIPayment("tx-upi-2", 50, target, target.getAccountOrUpi(), "0000");
        try {
            upiBadPin.processPayment();
        } catch (TransactionFailedException e) {
            LoggerUtil.logError("UPI auth failed (masked): " + LoggerUtil.maskSensitive(e.getCause().getMessage()));
        }
        Payment[] payments = new Payment[] {
                new CreditCardPayment("tx-card-1", 1500, target, "4111111111111111", "John Doe"),
                new UPIPayment("tx-upi-3", 200, target, target.getAccountOrUpi(), "2222"),
                new WalletPayment("tx-w-2", 50, target, new Wallet("user2", 20)), 
                new NetBankingPayment("tx-bank-1", 300, target, "1234567890")
        };

        for (Payment p : payments) {
            try {
                Transaction t = p.processPayment();
                LoggerUtil.logInfo("Processed: " + t);
            } catch (TransactionFailedException e) {
                LoggerUtil.logWarn("Transaction " + p.getId() + " failed: " + e.getCause().getMessage());

                if (p instanceof Retryable) {
                    try {
                        ((Retryable) p).retry();
                    } catch (Exception retryEx) {
                        LoggerUtil.logError("Retry failed: " + retryEx.getMessage());
                    }
                }

                if (p instanceof Refundable) {
                    try {
                        ((Refundable) p).refund(0);
                    } catch (Exception refundEx) {
                        LoggerUtil.logError("Refund failed: " + refundEx.getMessage());
                    }
                }
            } catch (NullPointerException npe) {
                LoggerUtil.logError("Null encountered during processing: " + npe.getMessage());
            }
        }
        try {
            WalletPayment nullWalletPayment = new WalletPayment("tx-null-1", 10, target, null);
            nullWalletPayment.processPayment();
        } catch (TransactionFailedException e) {
            LoggerUtil.logError("Handled NPE wrapper: " + e.getCause());
        }

        LoggerUtil.logInfo("SmartPayment Gateway Simulator finished.");
    }
    private static Beneficiary findBeneficiary(Beneficiary[] array, String id) throws BeneNotFoundException {
        if (array == null) throw new IllegalArgumentException("Beneficiary array is null");
        for (Beneficiary b : array) {
            if (b != null && b.getId().equals(id)) return b;
        }
        throw new BeneNotFoundException("Beneficiary with id " + id + " not found");
    }
}
