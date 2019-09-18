package com.revature.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.revature.utilities.MiscUtil;

public class Transaction {

	private int id;
	private int accountId;
	private String shortDesc;
	private String detailDesc;
	private String dateTime;
	private double value;

	public Transaction(int id, int accountId, String shortDesc, String detailDesc, String dateTime, double d) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.shortDesc = shortDesc;
		this.detailDesc = detailDesc;
		this.dateTime = dateTime;
		this.value = d;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountId=" + accountId + ", shortDesc=" + shortDesc + ", detailDesc="
				+ detailDesc + ", dateTime=" + dateTime + ", value=" + value + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}