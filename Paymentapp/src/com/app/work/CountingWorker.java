package com.app.work;

public class CountingWorker extends Thread {
	ProcessCounter obj;
	
	CountingWorker(ProcessCounter obj){
		this.obj = obj;
	}
	  @Override
	    public void run() {
	        for (int i = 0; i < 200; i++) {
	            try {
	                obj.decrement();
	                System.out.println("Counter value after decrement: " + obj.getvalue());
	            } catch (InterruptedException e) {
	                System.out.println("Thread was interrupted: " + e.getMessage());
	            }
	}
}
}
