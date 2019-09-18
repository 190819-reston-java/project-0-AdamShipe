//AShipe Project 0 Banking
package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.revature.controller.LoginScreen;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.UserAccount;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOImplAJDBC;
import com.revature.service.TransactionService;
import com.revature.utilities.ConnectionUtility;
import com.revature.utilities.MiscUtil;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		ConnectionUtility.getConnection();

		LoginScreen.blankLogin();
		
	}
}
