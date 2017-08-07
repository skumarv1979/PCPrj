package com.skumarv.impl;

import java.util.ArrayList;
import java.util.List;

import com.skumarv.pc.SubmitTasks;

public class ThreadCooperationTest {
	public static void main(String[] args) {
		SubmitTasks<ProducerWorkerImpl, Integer> obj = new SubmitTasks<ProducerWorkerImpl, Integer>();
		List<ProducerWorkerImpl> prdWrkLst = new ArrayList<ProducerWorkerImpl>();
		ProducerWorkerImpl ref = new ProducerWorkerImpl(10000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(7000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(10l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl(3000l, 935);
		prdWrkLst.add(ref);
		Integer in = obj.submit(prdWrkLst);
		System.out.println("Got Response :: "+in);
	}
}
