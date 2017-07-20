package com.skumarv.pc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SplitList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> lst = new ArrayList<String>();
		lst.add("One");
		lst.add("Two");
		lst.add("Three");
		lst.add("Four");
		lst.add("Five");
		System.out.println(chunk(lst, 2));
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
