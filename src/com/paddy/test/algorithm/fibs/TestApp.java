package com.paddy.test.algorithm.fibs;

public class TestApp {

	public static int fib(int n) {
		// 1 1 2 3 5 8
		if (n < 1) {
			throw new IllegalArgumentException("");
		} else if (n == 1 || n == 2) {
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

	public static int fib2(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("");
		} else if (n == 1 || n == 2) {
			return 1;
		}

		int f1 = 1;
		int f2 = 1;
		int tmp;
		for (int i = 3; i <= n; i++) {
			tmp = f1;
			f1 = f1 + f2;
			f2 = tmp;
			// System.out.print(String.format("%d ", f1));
		}

		return f1;
	}

	public static void main(String[] args) {
		int n = 40;
		long ts1 = System.currentTimeMillis();
		System.out.println(String.format("fib2(%d)=%d", n, fib2(n)));
		long ts2 = System.currentTimeMillis();
		System.out.println("TIME cost:" + (ts2 - ts1));
		long ts3 = System.currentTimeMillis();
		System.out.println(String.format("fib(%d)=%d", n, fib(n)));
		long ts4 = System.currentTimeMillis();
		System.out.println("TIME cost:" + (ts4 - ts3));
	}

}
