package com.paddy.test.algorithm.sort;

import com.paddy.test.algorithm.sort.support.SortUtil;

public class SelectionSort implements SortUtil.Sort {

	public void sort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			int lowIndex = i;
			for (int j = data.length - 1; j > i; j--) {
				if (data[j] < data[lowIndex]) {
					lowIndex = j;
				}
			}
			SortUtil.swap(data, i, lowIndex);
		}
	}

}