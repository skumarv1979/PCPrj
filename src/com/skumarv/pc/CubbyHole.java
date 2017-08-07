package com.skumarv.pc;

public class CubbyHole<U> {
	private ProducerResponse<U> contents;
	private boolean available = false;

	public synchronized ProducerResponse<U> get() {
		System.out.println("Consumer starts");
		while (available == false) {
			try {
				wait(10l);
			} catch (InterruptedException e) {
			}
		}
		available = false;
		notifyAll();
		System.out.println("Consumed :"+Thread.currentThread().getName()+", " + contents);
		return contents;
	}

	public synchronized void put(ProducerResponse<U> prdResp) {
		System.out.println("producers starts..."+Thread.currentThread().getName());
			try {
				notifyAll();
				while (available == true) {
				if ((!Thread.currentThread().isInterrupted())
						&&(contents==null || (contents != null && contents.getValue() == null))) {
					wait();
				}
				}
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		//System.out.println(Thread.currentThread().getName()+" Wait is over");
		if (contents == null
				|| (contents != null && contents.getValue() == null))
			contents = prdResp;
		available = true;
		notifyAll();
		System.out.println("Produced : "+Thread.currentThread().getName()+", " + prdResp);
	}

}
