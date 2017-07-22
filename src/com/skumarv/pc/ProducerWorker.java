package com.skumarv.pc;

public interface ProducerWorker <U> {
	public U execute() throws InterruptedException;
}
