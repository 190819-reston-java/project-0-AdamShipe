package com.revature.repository;

import java.util.List;

import com.revature.model.Account;

public interface AccountDAO {
	
	Account getAccount(int id);
	
	Account getAccount(String userName);
	
	boolean newAccount(Account a);
	
	boolean updateAccount(Account a);
	
	boolean deleteAccount(Account a);
	
	List<Account>getAccounts();

}
