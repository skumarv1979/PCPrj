package com.skumarv.pc;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerTest {
	private Integer value;
	public static void main(String[] args) {
		ProducerConsumerTest obj = new ProducerConsumerTest();
		obj.testThread();
	}
	public synchronized void testThread() {
		CubbyHole c = new CubbyHole();
		Producer p1 = new Producer(c, 1, null, 10000, "Thread-1");
		Producer p2 = new Producer(c, 2, null, 7000, "Thread-2");
		Producer p3 = new Producer(c, 3, 789, 5000, "Thread-3");
		Producer p4 = new Producer(c, 4, null, 3000, "Thread-4");
		List<Producer> lst = new ArrayList<Producer>();
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);
		lst.add(p4);
		Consumer c1 = new Consumer(c, 1, lst, this);
		/*p1.start();
		p2.start();
		p3.start();
		p4.start();*/
		c1.start();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I got the Repsonse :: "+value);
	}
	public synchronized void notifyAll(Integer value) {
		this.value = value;
		notifyAll();
	}
}
