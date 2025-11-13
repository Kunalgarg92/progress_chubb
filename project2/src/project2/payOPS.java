package project2;
import project2.Benificiary;
import java.io.*;
import java.util.ArrayList;
import java.util.*;


public class payOPS {
	
	public static void main(String[] args) {
		Benificiary acct = new Benificiary(34526272,443);
		Benificiary acct2 = new Benificiary(67890123, 600);
		boolean result = fundtransfrer(acct, acct2);
		System.out.println("Account details " + acct.account()+" balance " + acct.balance());
		System.out.println("Fund transfer result: " + result);
	}
	
	public static boolean fundtransfrer(Benificiary acct , Benificiary acct2) {
		var balance = acct2.balance();
		var accountno = acct2.account();
		var nameList = new ArrayList<String>();
		var companyName = new HashMap<String,List<Benificiary>>();
		System.out.println("balance " + balance);
		System.out.println("accountno " + accountno);
		return false;
	}
}
