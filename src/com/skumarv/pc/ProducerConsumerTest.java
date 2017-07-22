package com.skumarv.pc;

import java.util.ArrayList;
import java.util.List;

import com.skumarv.impl.ProducerWorkerImpl;

public class ProducerConsumerTest {
	private Integer value;
	public static void main(String[] args) {
		ProducerConsumerTest obj = new ProducerConsumerTest();
		List<ProducerWorker> prdWrkLst = new ArrayList<ProducerWorker>();
		ProducerWorker ref = new ProducerWorkerImpl(10000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(7000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(5000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(3000l, 833);
		prdWrkLst.add(ref);
		obj.testThread(prdWrkLst);
	}
	public synchronized void testThread(List<? extends ProducerWorker> lst) {
		CubbyHole c = new CubbyHole();
		if(lst!=null) {
			int thrdCnt = 0;
			List<Producer> producerLst = new ArrayList<Producer>();
			for (ProducerWorker producerWorker : lst) {
				thrdCnt++;
				Producer p = new Producer(c, thrdCnt, producerWorker, "Thread-"+thrdCnt);
				producerLst.add(p);
			}
			Consumer c1 = new Consumer(c, 1, producerLst, this);
			c1.start();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("I got the Repsonse :: "+value);
	}
	public synchronized void notifyAll(Integer value) {
		this.value = value;
		notifyAll();
	}
}
