package com.revature.service;

import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import com.revature.controller.AccountHome;
import com.revature.exception.BalanceLimitException;
import com.revature.exception.NegativeTransactionException;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOImplAJDBC;
import com.revature.repository.TransactionDAO;
import com.revature.repository.TransactionDAOImplAJDBC;
import com.revature.utilities.MiscUtil;
import com.revature.utilities.AnimationUtility;

public class AccountService {
	// Account object to contain the information of the account being accessed
	public static Account selectedAccount = new Account(0, "username", "12345", "first_name", "last_name", "01/01/1900",
			"bl@nk", 0, true);

	private static AccountDAO accountDAO = new AccountDAOImplAJDBC();

	public Account getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(Account selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public void changeSelectedAccount(String userName) {
		setSelectedAccount(accountDAO.getAccount(userName));
	}

	public static void withdrawTransaction(double d)
			throws InterruptedException, NegativeTransactionException, BalanceLimitException {
		if (d <= 0) {
			throw new NegativeTransactionException();
		} else {
			double currentBalance = selectedAccount.getBalance();
			if (d <= currentBalance) {
				selectedAccount.setBalance(selectedAccount.getBalance() - d);
				accountDAO.updateAccount(selectedAccount);

				System.out.print('\n');

				System.out.print(MiscUtil.successColor);
				MiscUtil.indent();
				TransactionDAOImplAJDBC.onlineTransaction(selectedAccount.getId(), "PZero Bank", "Online Withdrawal", -d);

				MiscUtil.logger.trace("withdrew $" + d + " from account " + selectedAccount.getId() + '\n');
				System.out.print(MiscUtil.RESET);
				TimeUnit.MILLISECONDS.sleep(300 * AnimationUtility.animationToggle);

				AnimationUtility.typingByWord("  Please remove the money from the tray." + '\n', 50, -10);
				TimeUnit.MILLISECONDS.sleep(800 * AnimationUtility.animationToggle);
				AnimationUtility.typingByWord("  What else can we help you with?", 42, 0);

				TimeUnit.MILLISECONDS.sleep(400 * AnimationUtility.animationToggle);
			} else {
				throw new BalanceLimitException();

			}
		}
	}

	public static void depositTransaction(double d) throws InterruptedException, NegativeTransactionException {
		if (d <= 0) {
			throw new NegativeTransactionException();
		} else {
			selectedAccount.setBalance(selectedAccount.getBalance() + d);
			accountDAO.updateAccount(selectedAccount);

			// Logger indicates successful transaction, message prints in purple
			System.out.print(MiscUtil.successColor);
			MiscUtil.indent();
			MiscUtil.logger.trace("deposited $" + d + " into account " + selectedAccount.getId() + '\n');
			TransactionDAOImplAJDBC.onlineTransaction(selectedAccount.getId(), "PZero Bank", "Online Deposit", d);
			System.out.print(MiscUtil.RESET);
			System.out.println();

			AnimationUtility.typingByWord("  What else can we help you with?", 42, 0);
			TimeUnit.MILLISECONDS.sleep(400 * AnimationUtility.animationToggle);
		}
	}

	public static void toggleAnimationSetting() {
		boolean pref = selectedAccount.getAnimationPref();
		if (pref == true) {
			selectedAccount.setAnimationPref(false);
			MiscUtil.indent(8);
			System.out.println(MiscUtil.successColor + "ANIMATIONS DEACTIVATED" + MiscUtil.RESET);
			System.out.println('\n');
			AnimationUtility.animationToggle = 0;
		} else {
			selectedAccount.setAnimationPref(true);
			MiscUtil.indent(8);
			AnimationUtility.typingByLetter(MiscUtil.successColor + "ANIMATIONS ACTIVATED" + MiscUtil.RESET, 50, 2);
			AnimationUtility.animationToggle = 1;
			System.out.println('\n');
		}
		accountDAO.updateAccount(selectedAccount);
	}

}
