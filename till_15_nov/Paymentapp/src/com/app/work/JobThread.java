package com.app.work;

public class JobThread extends Thread {
	JobThread(){
		
	}
	public JobThread(String name) {
		super(name);
	}
	public void run() {
		//write all the logic here which task need to be done
		System.out.print("Inside runtime");
		System.out.print(isAlive());
	}
}
