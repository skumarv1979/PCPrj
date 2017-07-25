package com.skumarv.impl;

import com.skumarv.pc.ProducerWorker;

public class ProducerWorkerImpl<U> implements ProducerWorker<U> {
	private long processingTime;
	private U returnValue;

	public ProducerWorkerImpl() {
		// TODO Auto-generated constructor stub
	}

	public ProducerWorkerImpl(long processingTime, U returnValue) {
		// TODO Auto-generated constructor stub
		this.processingTime = processingTime;
		this.returnValue = returnValue;
	}

	@Override
	public U execute() throws InterruptedException {
		try {
			if (!Thread.currentThread().isInterrupted()) {
				Thread.sleep(processingTime);
			}
		} catch (InterruptedException e) {
			// e.printStackTrace();
			throw e;
		}
		return returnValue;
	}

}
