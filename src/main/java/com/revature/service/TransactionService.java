package com.revature.service;

import java.util.List;

import com.revature.model.Transaction;
import com.revature.repository.TransactionDAO;
import com.revature.repository.TransactionDAOImplAJDBC;

public class TransactionService {
		
	public static TransactionDAO transactionDAO = new TransactionDAOImplAJDBC();
	
	public static List<Transaction> getTransactions(){
		return transactionDAO.getTransactions();
	}

}
