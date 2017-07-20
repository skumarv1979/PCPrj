package com.skumarv.pc;

public class Producer extends Thread {
	private CubbyHole cubbyhole;
	private int number;
	private Integer value;
	private long processingTime;

	public Producer(CubbyHole c, int number, Integer value,
			long processingTime, String name) {
		cubbyhole = c;
		this.number = number;
		this.value = value;
		this.processingTime = processingTime;
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
			Producer tmp = (Producer) obj;
			return this.getName().equals(tmp.getName());
		}
		return false;
	}

	public void run() {
		try {
			sleep(processingTime);
			ProducerResponse resp = new ProducerResponse();
			resp.setValue(value);
			resp.setProducer(this);
			cubbyhole.put(resp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// System.out.println("Producer #" + this.number + " put: " + value);
	}
}