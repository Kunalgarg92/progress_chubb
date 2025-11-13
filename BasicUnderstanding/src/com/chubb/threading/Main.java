package com.chubb.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		worker worker = new worker();
		worker.start();
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(worker);

	}

}
