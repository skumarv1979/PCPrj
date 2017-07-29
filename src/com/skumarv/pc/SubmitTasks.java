package com.skumarv.pc;

import java.util.ArrayList;
import java.util.List;

public class SubmitTasks <T extends ProducerWorker<U>, U> {
	private U value;
	public synchronized U submit(List<T> lst) {
		CubbyHole<U> c = new CubbyHole<U>();
		if(lst!=null) {
			int thrdCnt = 0;
			List<Producer<U>> producerLst = new ArrayList<Producer<U>>();
			for (ProducerWorker<U> producerWorker : lst) {
				thrdCnt++;
				Producer<U> p = new Producer<U>(c, producerWorker, "Thread-"+thrdCnt);
				producerLst.add(p);
			}
			Consumer<T, U> c1 = new Consumer<T, U>(c, 1, producerLst, this);
			c1.start();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("I got the Repsonse :: "+value);
		return value;
	}
	public synchronized void notifyAll(U value) {
		this.value = value;
		notifyAll();
	}
}
