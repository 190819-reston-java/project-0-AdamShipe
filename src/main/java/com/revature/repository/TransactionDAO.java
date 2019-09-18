package com.revature.repository;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDAO {
	
//	Transaction getTransaction(int id);
	
	boolean newTransaction(Transaction t);
	
	List<Transaction>getTransactions();
	
	public static boolean onlineTransaction(Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	List<Transaction>getTransactions(int userId);

}
