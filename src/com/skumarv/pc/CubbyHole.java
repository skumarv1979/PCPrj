package com.skumarv.pc;

public class CubbyHole {
	private ProducerResponse contents;
	private boolean available = false;

	public synchronized ProducerResponse get() {
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		notifyAll();
		System.out.println("Consumed :" + contents);
		return contents;
	}

	public synchronized void put(ProducerResponse prdResp) {
		while (available == true || (contents!=null && contents.getValue()!=null)) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if(contents==null || (contents!=null && contents.getValue()==null))
			contents = prdResp;
		available = true;
		notifyAll();
		System.out.println("Produced :" + prdResp);
	}
	
}
