package com.app.work;

public class JobProcessing {
	public static void main(String[] args) {
		
		WorkThread obj = new WorkThread();
		Thread t2 = new Thread(obj);
		t2.start();
		JobThread t1 = new JobThread();
		t1.start();
		System.out.println("thread details");

}
}
