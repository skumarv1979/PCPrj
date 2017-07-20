package com.skumarv.pc;

public class ProducerResponse {
	private Integer value;
	private Producer producer;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value==null?null:this.value.toString();
	}
}
