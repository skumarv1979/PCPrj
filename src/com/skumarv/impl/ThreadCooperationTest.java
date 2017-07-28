package com.skumarv.impl;

import java.util.ArrayList;
import java.util.List;

import com.skumarv.pc.SubmitTasks;

public class ThreadCooperationTest {
	public static void main(String[] args) {
		SubmitTasks<ProducerWorkerImpl<Integer>, Integer> obj = new SubmitTasks<ProducerWorkerImpl<Integer>, Integer>();
		List<ProducerWorkerImpl<Integer>> prdWrkLst = new ArrayList<ProducerWorkerImpl<Integer>>();
		ProducerWorkerImpl<Integer> ref = new ProducerWorkerImpl<Integer>(10000l, 123);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl<Integer>(7000l, null);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl<Integer>(2000l, 789);
		prdWrkLst.add(ref);
		ref = new ProducerWorkerImpl<Integer>(3000l, 833);
		prdWrkLst.add(ref);
		obj.submit(prdWrkLst);

	}
}
