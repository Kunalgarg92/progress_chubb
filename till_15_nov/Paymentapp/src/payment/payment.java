package payment;
import java.util.*;

public class payment implements Comparable<payment> {
	  private String accountHolderName;
	    private long accountNo;
	    private String transCode;
	    private String country;
	    private String ifscCode;
	    private double balance;

	    public payment(String accountHolderName, long accountNo, String transCode, String country, String ifscCode, double balance) {
	        this.accountHolderName = accountHolderName;
	        this.accountNo = accountNo;
	        this.transCode = transCode;
	        this.country = country;
	        this.ifscCode = ifscCode;
	        this.balance = balance;
	    }
	    public String getAccountHolderName() {
	        return accountHolderName;
	    }

	    public long getAccountNo() {
	        return accountNo;
	    }

	    public String getTransCode() {
	        return transCode;
	    }
	    public String getCountry() {
	        return country;
	    }

	    public String getIfscCode() {
	        return ifscCode;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    @Override
	    public String toString() {
	        return accountHolderName + " | " + accountNo + " | " + balance;
	    }
	    @Override
	    public int compareTo(payment other) {
	        return this.accountHolderName.compareToIgnoreCase(other.accountHolderName);
	    }
	    
	    public static Comparator<payment> AccountNoComparator = new Comparator<payment>() {
	        @Override
	        public int compare(payment a1, payment a2) {
	            return Long.compare(a1.getAccountNo(), a2.getAccountNo());
	        }
	    };
	    public static Comparator<payment> BalanceComparator = new Comparator<payment>() {
	        @Override
	        public int compare(payment a1, payment a2) {
	            return Double.compare(a1.getBalance(), a2.getBalance());
	        }
	    };
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (!(obj instanceof payment)) return false;
	        payment other = (payment) obj;
	        return this.accountNo == other.accountNo &&
	               Objects.equals(this.accountHolderName, other.accountHolderName);
	    }
	    @Override
	    public int hashCode() {
	        return Objects.hash(accountHolderName, accountNo);
	    }
	    
	public static void main(String[] args) {
		 List<payment> accounts = new ArrayList<>();
	        accounts.add(new payment ("lakshay", 103, "T1", "India", "HDFC0001", 20000));
	        accounts.add(new payment ("Kunal", 101, "T2", "India", "HDFC0002", 15000));
	        accounts.add(new payment("himanshu", 102, "T3", "India", "HDFC0003", 50000));
	        accounts.add(new payment ("lakshay", 103, "T1", "India", "HDFC0001", 20000));
	        System.out.println("Original List:");
	        accounts.forEach(System.out::println);
	        Collections.sort(accounts);
	        System.out.println("\nSorted by Account Holder Name (Comparable):");
	        accounts.forEach(System.out::println);
	        Collections.sort(accounts, payment.AccountNoComparator);
	        System.out.println("\nSorted by Account Number (Comparator):");
	        accounts.forEach(System.out::println);
	        Collections.sort(accounts, payment.BalanceComparator);
	        System.out.println("\nSorted by Balance (Comparator):");
	        accounts.forEach(System.out::println);
	        Set<payment> set = new HashSet<>(accounts);
	        System.out.println("\nUnique Accounts (using HashSet):");
	        set.forEach(System.out::println);
	    }

	}
