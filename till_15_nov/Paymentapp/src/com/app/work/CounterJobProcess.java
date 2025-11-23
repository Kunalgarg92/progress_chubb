package com.app.work;

public class CounterJobProcess {
	public static void main(String[] args) {
		ProcessCounter countobj = new ProcessCounter();
		CountingWorker2 inobj = new CountingWorker2(countobj);
		CountingWorker dobj = new CountingWorker(countobj);
		inobj.start();
		dobj.start();
		System.out.println("value of counter" + countobj.getvalue());
		System.out.println("value of counter atomic " + countobj.getatomiccounterValue());
	}
}
