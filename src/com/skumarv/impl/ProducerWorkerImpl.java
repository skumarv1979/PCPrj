package com.skumarv.impl;

import com.skumarv.pc.ProducerWorker;

public class ProducerWorkerImpl implements ProducerWorker<Integer> {
	private long processingTime;
	private Integer returnValue;

	public ProducerWorkerImpl() {
		// TODO Auto-generated constructor stub
	}

	public ProducerWorkerImpl(long processingTime, Integer returnValue) {
		// TODO Auto-generated constructor stub
		this.processingTime = processingTime;
		this.returnValue = returnValue;
	}

	@Override
	public Integer execute() throws InterruptedException {
		/*try {
			if (!Thread.currentThread().isInterrupted()) {
				Thread.sleep(processingTime);
			}
		} catch (InterruptedException e) {
			// e.printStackTrace();
			throw e;
		}*/
		return returnValue;
	}

}
