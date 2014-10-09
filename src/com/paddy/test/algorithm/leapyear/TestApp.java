package com.paddy.test.algorithm.leapyear;

public class TestApp {

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static void main(String[] args) {
		int begin = 1986;
		int end = 2100;
		System.out.println(String.format(
				"Show leap year between year %s and %s", begin, end));
		int colNum = 10;
		int tmp = 0;
		for (int i = begin; i <= end; i++) {
			if (isLeapYear(i)) {
				System.out.print(String.format("%5s", i));
				if (++tmp % colNum == 0) {
					System.out.println();
				}
			}
		}
	}

}
