package com.java.common.pojo;

import java.io.Serializable;
import java.util.List;

public class UIDataResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long totaLong;
	private List rows;
	public Long getTotaLong() {
		return totaLong;
	}
	public void setTotaLong(Long totaLong) {
		this.totaLong = totaLong;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
