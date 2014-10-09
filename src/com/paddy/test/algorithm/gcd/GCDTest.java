package com.paddy.test.algorithm.gcd;

public class GCDTest {

	public static long gcd(long m, long n) {
		if (m < n) {
			long tmp = m;
			m = n;
			n = tmp;
		}

		long tmp;
		while (m % n != 0) {
			tmp = m;
			m = n;
			n = tmp % n;
		}

		return n;
	}

	public static long lcm(long m, long n) {
		return m * n / gcd(m, n);
	}

	public static void main(String[] args) {
		long m = 49000;
		long n = 350;
		System.out.println(String.format("GCD(%d, %d)=%d  LCM(%d, %d)=%d", m,
				n, gcd(m, n), m, n, lcm(m, n)));
		
		
		for (char ch : String.valueOf(m).toCharArray()) {
			System.out.println(String.format("%c  %d  %d", ch, (int) ch,
					ch - '0'));
		}
	}

}
