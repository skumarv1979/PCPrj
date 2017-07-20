package com.skumarv.pc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Consumer extends Thread {
	private CubbyHole cubbyhole;
	private int number;
	private List<Producer> producerThreadLst = null;
	private ProducerConsumerTest prodConsTest = null;

	public List<Producer> getProducerThreadLst() {
		return producerThreadLst;
	}

	public void setProducerThreadLst(List<Producer> producerThreadLst) {
		this.producerThreadLst = producerThreadLst;
	}

	public Consumer(CubbyHole c, int number, List<Producer> producerThreadLst, ProducerConsumerTest prodConsTest) {
		cubbyhole = c;
		this.number = number;
		this.producerThreadLst = producerThreadLst;
		this.prodConsTest = prodConsTest;
	}

	public void run() {
		ProducerResponse value = null;
		Iterable<Iterable<Producer>> splitList = chunk(producerThreadLst, 2);
		for (Iterable<Producer> iterable : splitList) {
			for (Producer producer : iterable) {
				producer.start();
			}
			Set<Producer> set = new HashSet<Producer>();
			boolean gotResponse = false;
			Integer response = null;
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
				for (Producer producer : iterable) {
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

	public static <T> Iterable<Iterable<T>> chunk(Iterable<T> in, int size) {
		List<Iterable<T>> lists = new ArrayList<Iterable<T>>();
		Iterator<T> i = in.iterator();
		while (i.hasNext()) {
			List<T> list = new ArrayList<T>();
			for (int j = 0; i.hasNext() && j < size; j++) {
				list.add(i.next());
			}
			lists.add(list);
		}
		return lists;
	}
}
