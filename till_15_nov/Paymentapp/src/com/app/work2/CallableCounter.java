package com.app.work2;

import java.util.concurrent.*;

public class CallableCounter implements Callable<String> {
	public String call() throws Exception{
		return "success";
	}

}
