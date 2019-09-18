package com.revature.repository;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDAO {
	
	boolean newTransaction(Transaction t);
	
	List<Transaction>getTransactions();
	
	public static boolean onlineTransaction(Transaction t) {
		return false;
	}
	
}
