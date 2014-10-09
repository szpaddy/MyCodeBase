package com.paddy.test.inputparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Param {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("(Scanner)Please input a string:");
		Scanner sc = new Scanner(System.in);
		String p1 = sc.next();
		System.out.println("scanner.next():" + p1);

		System.out.println("(BufferedReader)Please input a string:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String p2 = br.readLine();

			System.out.println("BufferedReader.readline():" + p2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
