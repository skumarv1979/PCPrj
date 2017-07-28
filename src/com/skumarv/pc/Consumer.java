package com.skumarv.pc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Consumer<T extends ProducerWorker<U>, U> extends Thread {
	private CubbyHole<U> cubbyhole;
	private List<Producer<U>> producerThreadLst = null;
	private SubmitTasks<T, U> prodConsTest = null;

	public List<Producer<U>> getProducerThreadLst() {
		return producerThreadLst;
	}

	public void setProducerThreadLst(List<Producer<U>> producerThreadLst) {
		this.producerThreadLst = producerThreadLst;
	}

	public Consumer(CubbyHole<U> c, int number,
			List<Producer<U>> producerThreadLst, SubmitTasks<T, U> prodConsTest) {
		cubbyhole = c;
		this.producerThreadLst = producerThreadLst;
		this.prodConsTest = prodConsTest;
	}

	public void run() {
		ProducerResponse<U> value = null;
		int thrd = 2;
		LinkedList<Producer<U>> executingProducers = new LinkedList<Producer<U>>();
		if (producerThreadLst != null && !producerThreadLst.isEmpty()) {
			for (int i = 0; i < thrd; i++) {
				if (i < producerThreadLst.size()) {
					producerThreadLst.get(i).start();
					executingProducers.add(producerThreadLst.get(i));
				}
			}
			int producerIdx = thrd;
			boolean gotResponse = false;
			U response = null;
			while (true) {
				value = cubbyhole.get();
				executingProducers.remove(value.getProducer());
				if (value.getValue() != null) {
					gotResponse = true;
					response = value.getValue();
					break;
				}
				if (producerIdx < producerThreadLst.size()) {
					producerThreadLst.get(producerIdx).start();
					executingProducers.add(producerThreadLst.get(producerIdx));
					producerIdx++;
				}
			}
			if (gotResponse) {
				for (Producer<U> producer : executingProducers) {
					producer.interrupt();
				}
				prodConsTest.notifyAll(response);
			}
		}
	}

	public <B> Iterable<Iterable<B>> chunk(Iterable<B> in, int size) {
		List<Iterable<B>> lists = new ArrayList<Iterable<B>>();
		Iterator<B> i = in.iterator();
		while (i.hasNext()) {
			List<B> list = new ArrayList<B>();
			for (int j = 0; i.hasNext() && j < size; j++) {
				list.add(i.next());
			}
			lists.add(list);
		}
		return lists;
	}
}
