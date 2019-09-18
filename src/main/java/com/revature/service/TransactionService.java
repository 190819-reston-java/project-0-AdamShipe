package com.revature.service;

import java.util.List;

import com.revature.model.Transaction;
import com.revature.repository.TransactionDAO;
import com.revature.repository.TransactionDAOImplAJDBC;

public class TransactionService {
	
//	public static Transaction selectedTransaction = new Transaction(0, 0, "blank", "blaaaank", "01/01/1901", 1.00);
	
	public static TransactionDAO transactionDAO = new TransactionDAOImplAJDBC();
	
	public static List<Transaction> getTransactions(){
		return transactionDAO.getTransactions();
	}
	
//	public List<Transaction> getTransactions(int userId){
//		return transactionDAO.getTransactions(userId);
//	}
	
//	public Transaction getAccountTransactions() {
//		this.
//	}

}
