package com.app.work;
import java.util.concurrent.atomic.*;
public class ProcessCounter {
	
	private volatile int  counter;
	
	private AtomicInteger counter1 =  new AtomicInteger();
	
	synchronized void increament()  throws InterruptedException{
		counter1.getAndIncrement();
		counter++;
		wait(20);
		notify();
	}
	synchronized void decrement()  throws InterruptedException{
		counter1.getAndDecrement();
		counter--;
		wait(20);
		notify();
	}
	
	public synchronized int getvalue() {
		return counter;
	}
	
	public synchronized  int getatomiccounterValue() {
		return counter1.get();
	}
}
