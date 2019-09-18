package com.revature.testables;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.controller.AccountHome;
import com.revature.exception.BalanceLimitException;
import com.revature.exception.NegativeTransactionException;
import com.revature.model.Account;
import com.revature.repository.AccountDAOImplAJDBC;
import com.revature.service.AccountService;
import com.revature.utilities.ConnectionUtility;
import com.revature.utilities.StreamCloser;

public class TransactionTest {
	
	public static Account selectedAccount = new Account(0, "username", "12345", "first_name", "last_name", "01/01/1900", "bl@nk", 0, true);
	private static AccountService accountService = new AccountService();
	
	/**
	 * Testing that the withdraw method subtracts from the server balance:
	 * @throws BalanceLimitException 
	 * @throws NegativeTransactionException 
	 */
	@Test
	public void testWithdraw() throws InterruptedException, NegativeTransactionException, BalanceLimitException {
		AccountService.withdrawTransaction(124.88);
		assertTrue("Not working",AccountService.selectedAccount.getBalance() == 11875.12);
		System.out.println("Step 3");
	}

	/**
	 * testing that the withdraw method will reject withdrawals greater than
	 *	the account balance and leave the balance untouched on the server:
	 * @throws BalanceLimitException 
	 * @throws NegativeTransactionException 
	 */
	@Test
	public void testWithdrawRejection() throws InterruptedException, NegativeTransactionException, BalanceLimitException {
		double startingBalance = AccountService.selectedAccount.getBalance();
		AccountService.withdrawTransaction(50000.00);
		double endingBalance = AccountService.selectedAccount.getBalance();
		assertTrue("uh oh",startingBalance == endingBalance);
		System.out.println("      ____withdrawalREJECTION test complete____" + '\n');		
	}

	/**
	 * Testing that the depositTransaction method adds to the server balance:
	 * @throws NegativeTransactionException 
	 */
	@Test
	public void testDeposit() throws InterruptedException, NegativeTransactionException {
		AccountService.depositTransaction(291.33);
		double result = AccountService.selectedAccount.getBalance();
		assertTrue("we got a problem",result == 12166.45);
		System.out.println("          ____deposit test complete____" + '\n');
	}
	
	@BeforeClass
	static public void setUpAccount() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;		
		
		final String query = "UPDATE accounts SET user_name='p0_test', user_password='12345',"
				+ "first_name='p0_employee', last_name='sir/madam', birth_date='01/01/1901',"
				+ "email='pzero@pzbanks.com', balance=12000.00 WHERE id=1;";

		conn = ConnectionUtility.getConnection();
		stmt = conn.prepareStatement(query);

		stmt.execute();
		System.out.println("testing testing");
		
		accountService.changeSelectedAccount("p0_test");			
	}
	
	@AfterClass
	static public void restoreAccount() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;		
		
		final String query = "UPDATE accounts SET user_name='p0_test', user_password='12345',"
				+ "first_name='p0_employee', last_name='sir/madam', birth_date='01/01/1901',"
				+ "email='pzero@pzbanks.com', balance=12000.00 WHERE id=1;";

		conn = ConnectionUtility.getConnection();
		stmt = conn.prepareStatement(query);

		stmt.execute();					
		System.out.println("goodbye");
	}

}



