package com.paddy.test.algorithm.hanoi;

public class Hanoi {

	public static void move(int n, char from, char mid, char to) {
		if (n == 1) {
			System.out.println(String
					.format("%3d %c->%c->%c", n, from, mid, to));
		} else {
			move(n - 1, from, to, mid);
			System.out.println(String
					.format("%3d %c->%c->%c", n, from, mid, to));
			move(n - 1, mid, from, to);
		}
	}

	public static void main(String[] args) {
		move(3, 'A', 'B', 'C');
	}

}
