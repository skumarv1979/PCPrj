package com.skumarv.pc;

public class Producer<U> extends Thread {
	private CubbyHole<U> cubbyhole;
	private ProducerWorker<U> worker;
	private U value;

	public Producer(CubbyHole<U> c, ProducerWorker<U> worker, String name) {
		cubbyhole = c;
		this.worker = worker;
		this.setName(name);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof Producer) {
			@SuppressWarnings("unchecked")
			Producer<U> tmp = (Producer<U>) obj;
			return this.getName().equals(tmp.getName());
		}
		return false;
	}

	public void run() {
		try {
			value = worker.execute();
			ProducerResponse<U> resp = new ProducerResponse<U>();
			resp.setValue(value);
			resp.setProducer(this);
			if (!Thread.currentThread().isInterrupted()) {
				cubbyhole.put(resp);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// System.out.println("Producer #" + this.number + " put: " + value);
	}
}