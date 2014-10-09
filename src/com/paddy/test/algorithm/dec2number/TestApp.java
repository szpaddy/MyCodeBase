package com.paddy.test.algorithm.dec2number;

import java.util.Stack;

public class TestApp {

	public static String dec2Num(long dec, int r) {
		Stack<String> st = new Stack<String>();

		while (dec != 0) {
			st.push(String.valueOf(dec % r));
			dec = dec / r;
		}

		String ret = "";
		while (!st.empty()) {
			ret += st.pop();
		}
		return ret;
	}

	public static String decNum2(long dec, int r) {
		if (dec / r == 0) {
			return String.valueOf(dec);
		} else {
			return decNum2(dec / r, r) + String.valueOf(dec % r);
		}
	}

	public static void main(String[] args) {
		long dec = 100;
		int r = 6;
		System.out.println(String.format("dec2Num(%d, %d)=%s", dec, r,
				dec2Num(dec, r)));
		System.out.println(String.format("dec2Num2(%d, %d)=%s", dec, r,
				decNum2(dec, r)));
	}

}
