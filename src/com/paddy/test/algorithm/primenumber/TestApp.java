package com.paddy.test.algorithm.primenumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestApp {

	public static boolean isPrimeNumber(int n) {
		if (n < 2) {
			return false;
		}

		int n1 = (int) Math.sqrt(n);
		for (int i = 2; i <= n1; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static List<Integer> decPrime(int n) {
		List<Integer> lst = new ArrayList<Integer>();

		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				lst.add(new Integer(i));
				n /= i;
			}
		}

		return lst;
	}

	public static void testIsPrimeNumber(int begin, int end) {
		System.out.println(String.format("Show prime number between %s and %s",
				begin, end));
		int colNum = 20;
		int tmp = 0;
		for (int i = begin; i <= end; i++) {
			if (isPrimeNumber(i)) {
				System.out.print(String.format("%4d", i));
				if (++tmp % colNum == 0) {
					System.out.println();
				}
			}
		}

	}

	private static void testDecPrime(int n) {
		System.out.println(String.format("show dec prime for %d", n));
		List<Integer> lst = decPrime(n);
		for (Integer obj : lst) {
			System.out.print(String.format("%d ", obj.intValue()));
		}
	}

	public static void main(String[] args) {
		//testIsPrimeNumber(100, 900);
		testDecPrime(1000);
	}

}
