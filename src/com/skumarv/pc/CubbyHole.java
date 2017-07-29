package com.skumarv.pc;

public class CubbyHole<U> {
	private ProducerResponse<U> contents;
	private boolean available = false;

	public synchronized ProducerResponse<U> get() {
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		notifyAll();
		//System.out.println("Consumed :" + contents);
		return contents;
	}

	public synchronized void put(ProducerResponse<U> prdResp) {
		while (available == true
				|| (contents != null && contents.getValue() != null)) {
			try {
				if (!Thread.currentThread().isInterrupted()) {
					wait();
				}
			} catch (InterruptedException e) {
			}
		}
		if (contents == null
				|| (contents != null && contents.getValue() == null))
			contents = prdResp;
		available = true;
		notifyAll();
		//System.out.println("Produced :" + prdResp);
	}

}
