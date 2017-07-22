package com.skumarv.pc;

public class ProducerResponse <U> {
	private U value;
	private Producer<U> producer;
	public U getValue() {
		return value;
	}
	public void setValue(U value) {
		this.value = value;
	}
	public Producer<U> getProducer() {
		return producer;
	}
	public void setProducer(Producer<U> producer) {
		this.producer = producer;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value==null?null:this.value.toString();
	}
}
