package com.skumarv.pc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Consumer <T extends ProducerWorker<U>, U> extends Thread {
	private CubbyHole<U> cubbyhole;
	private List<Producer<U>> producerThreadLst = null;
	private SubmitTasks<?, U> prodConsTest = null;

	public List<Producer<U>> getProducerThreadLst() {
		return producerThreadLst;
	}

	public void setProducerThreadLst(List<Producer<U>> producerThreadLst) {
		this.producerThreadLst = producerThreadLst;
	}

	public Consumer(CubbyHole<U> c, int number, List<Producer<U>> producerThreadLst, SubmitTasks<?, U> prodConsTest) {
		cubbyhole = c;
		this.producerThreadLst = producerThreadLst;
		this.prodConsTest = prodConsTest;
	}

	public void run() {
		ProducerResponse<U> value = null;
		Iterable<Iterable<Producer<U>>> splitList = chunk(producerThreadLst, 2);
		for (Iterable<Producer<U>> iterable : splitList) {
			for (Producer<U> producer : iterable) {
				producer.start();
			}
			Set<Producer<U>> set = new HashSet<Producer<U>>();
			boolean gotResponse = false;
			U response = null;
			int siz = 0;
			if (iterable instanceof Collection<?>) {
				siz = ((Collection<?>) iterable).size();
			}
			for (int i = 0; i < siz; i++) {
				value = cubbyhole.get();
				if (value.getProducer() != null) {
					set.add(value.getProducer());
				}
				if (value.getValue() != null) {
					gotResponse = true;
					response = value.getValue();
					break;
				}
				// System.out.println("Consumer #" + this.number + " got: " +
				// value);
			}
			if (gotResponse) {
				for (Producer<U> producer : iterable) {
					if (!set.contains(producer)) {
						producer.interrupt();
					}
				}
				prodConsTest.notifyAll(response);
				break;
			}
			//System.out.println(iterable);
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
