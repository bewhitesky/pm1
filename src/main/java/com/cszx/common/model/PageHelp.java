package com.cszx.common.model;

import java.util.ArrayList;
import java.util.List;

public class PageHelp<T> {
	private List<T> result;
	private int total;

	public PageHelp(List<T> list, int rows, int page) {
		this.result = new ArrayList<T>();
		this.total = list.size();
		while ((page - 1) != 0) {
			int max = rows * (page - 1);
			int min = rows * (page - 2);
			if (total < max && total > min) {
				page = page - 1;
				break;
			} else if (total > max) {
				break;
			} else {
				page = page - 1;
			}
		}
		for (int i = rows * (page - 1); i < rows * (page - 1) + rows; i++) {
			if (i >= total)
				break;
			result.add(list.get(i));

		}
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

}
