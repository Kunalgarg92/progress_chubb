package com.app.work;

public class CountingWorker2 extends Thread {
ProcessCounter obj;
	
	CountingWorker2(ProcessCounter obj){
		this.obj = obj;
	}
	public void run() {
		try {
		for(int i=0;i<200;i++) {
			obj.increament();
			System.out.println("counter value after increamnet " + obj.getvalue());
		}
		}catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
	}
}
