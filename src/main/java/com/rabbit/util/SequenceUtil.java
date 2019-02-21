package com.rabbit.util;

public class SequenceUtil {

	private final static Sequence sequence = new Sequence();

	public static String getNextId() {
		return "" + sequence.nextId();
	}
	public static long getNextId(int table) {
		String  orderNo=getNextId();

		return Long.parseLong(orderNo.substring(0, orderNo.length()-1)+table);
	}
public static void main(String[] args) {
	String nextId = SequenceUtil.getNextId();
	System.out.println(nextId);
}
	}