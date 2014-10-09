package com.paddy.test.algorithm.permutation;

public class TestApp {

	static int[] bits = new int[] { 1, 2, 3, 4 };

	public static void main(String[] args) {
		showPerm("", bits);
	}

	private static void showPerm(String prefix, int[] a) {
		if (a.length == 1) {
			System.out.println(String.format("%s ", prefix + a[0]));
		}

		for (int i = 0; i < a.length; i++) {
			showPerm(prefix + a[i], getSubArray(a, i));
		}
	}

	private static int[] getSubArray(int[] a, int index) {
		int[] b = new int[a.length - 1];
		System.arraycopy(a, 0, b, 0, index);
		System.arraycopy(a, index + 1, b, index, a.length - index - 1);
		return b;
	}
}
