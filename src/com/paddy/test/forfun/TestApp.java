package com.paddy.test.forfun;

public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "12345678";
		int move = 3;

		char[] chAry = str.toCharArray();
		char[] newChAry = new char[str.length()];
		System.arraycopy(chAry, move, newChAry, 0, chAry.length - move);
		System.arraycopy(chAry, 0, newChAry, chAry.length - move, move);

		System.out.println(String.format(
				"move head %d of [%s] to the end. result[%s]", move, str,
				new String(newChAry)));
	}

}
