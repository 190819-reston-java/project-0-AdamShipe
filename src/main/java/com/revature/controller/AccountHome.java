package com.revature.controller;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//import org.omg.IOP.TransactionService;

import com.revature.exception.BalanceLimitException;
import com.revature.exception.NegativeTransactionException;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOImplAJDBC;
import com.revature.service.AccountService;
import com.revature.utilities.MiscUtil;
import com.revature.utilities.AnimationUtility;
import com.revature.service.TransactionService;

public class AccountHome {

	public static Scanner sc = new Scanner(System.in);

	public static void accountWelcome() throws InterruptedException {
		System.out.print('\n' + "          ");
		AnimationUtility.typingByLetter("WELCOME BACK " + AccountService.selectedAccount.getFirstName().toUpperCase(),
				32, 0);
		System.out.println();
		mainMenu();
	}

	public static void mainMenu() throws InterruptedException {
		

		TimeUnit.MILLISECONDS.sleep(60 * AnimationUtility.animationToggle);
		System.out.println("  __________________________________________");
		System.out.println("  Press [A] to view your account balance");
		TimeUnit.MILLISECONDS.sleep(90 * AnimationUtility.animationToggle);
		System.out.println("  Press [W] to withdraw money");
		TimeUnit.MILLISECONDS.sleep(120 * AnimationUtility.animationToggle);
		System.out.println("  Press [D] to deposit money");
		TimeUnit.MILLISECONDS.sleep(150 * AnimationUtility.animationToggle);
		System.out.println("  Press [T] to view transactions");
		TimeUnit.MILLISECONDS.sleep(150 * AnimationUtility.animationToggle);
		System.out.println("  Press [S] for settings");
		TimeUnit.MILLISECONDS.sleep(180 * AnimationUtility.animationToggle);
		
		System.out.println("  Press [X] to logout");
		System.out.println("  __________________________________________" + '\n');

		//Alternate menu layout:
//		System.out.println("                [W] Withdraw   [E] Transactions");
//		System.out.println("  [A] Account    [S] Settings   [D] Deposit");
//		System.out.println(MiscUtil.DISABLED_COLOR + "   [Z] Back " +  MiscUtil.RESET + "      [X] Logout");

		selectionOptions();
	}

	// the interactive component of the Main Menu
	public static void selectionOptions() throws InterruptedException {

		System.out.print(MiscUtil.inputColor);
		MiscUtil.indent(4);
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);

		switch (userInput) {
		case "a": // supports both lower and upper-case userInputs
			accountBalance();
			break;
		case "A":
			accountBalance();
			break;
		case "s":
			settings();
			break;
		case "S":
			settings();
			break;
		case "w":
			withdraw();
			break;
		case "W":
			withdraw();
			break;
		case "d":
			deposit();
			break;
		case "D":
			deposit();
			break;
		case "x":
			confirmLogout();
			break;
		case "X":
			confirmLogout();
			break;
		case "e":
			transactions();
			break;
		case "E":
			transactions();
			break;
		case "t":
			transactions();
			break;
		case "T":
			transactions();
			break;
		default:
			System.out.println("Invalid input");
			selectionOptions();
			break;
		}
	}

	
	//TRANSACTION HISTORY
	private static void transactions() throws InterruptedException {

		System.out.println();
		MiscUtil.indent(8);
		AnimationUtility.typingByLetter("~~TRANSACTION HISTORY~~" + '\n', 65, 5);
		System.out.println();
		System.out.println("  Date:        Description:                                  Amount:");
		
		for (int i = 0; i < TransactionService.getTransactions().size(); i++) {
			if (i % 2 == 0) {
				System.out.print(MiscUtil.ROWS + MiscUtil.WHITE_TEXT);
			}
			System.out.println("  " + TransactionService.getTransactions().get(i).getDateTime().substring(0, 10) + "   "
					+ MiscUtil.fixLength((TransactionService.getTransactions().get(i).getShortDesc()
							+ " " + TransactionService.getTransactions().get(i).getDetailDesc()).toUpperCase(), 42)
					+ MiscUtil.frontPadding(MiscUtil.df2(Double.valueOf(MiscUtil
							.fixLength(Double.toString(TransactionService.getTransactions().get(i).getValue()), 10))), ' ', 10)
					+ "  " + MiscUtil.RESET);
			if(i>=25) {
				break;	//show a maximum of 25 rows
			}
		}
		System.out.println("`````````````````````````````````````````````````````````````````````" + '\n');

		AnimationUtility.typingByWord("  What else can we help you with?", 42, 0);
		TimeUnit.MILLISECONDS.sleep(400 * AnimationUtility.animationToggle);
		mainMenu();
	}

	//SETTINGS menu
	private static void settings() throws InterruptedException {
		MiscUtil.indent();
		AnimationUtility.typingByWord("First Name: " + AccountService.selectedAccount.getFirstName(), 30, 1);

		MiscUtil.indent();
		AnimationUtility.typingByWord("Last Name: " + AccountService.selectedAccount.getLastName(), 30, 1);

		MiscUtil.indent();
		AnimationUtility.typingByWord("P0 Account Name: " + AccountService.selectedAccount.getUserName(), 30, 1);

		MiscUtil.indent();
		AnimationUtility.typingByWord("Email Address: " + AccountService.selectedAccount.getEmail(), 30, 1);

		MiscUtil.indent();
		AnimationUtility.typingByWord(
				"Date of Birth: " + AccountService.selectedAccount.getBirthDate().substring(0, 10), 30, 1);

		MiscUtil.indent();
		if (AccountService.selectedAccount.getAnimationPref() == true) {
			System.out.println("Animations: ON");
		} else {
			System.out.println("Animations: OFF");
		}

		System.out.println('\n' + "     [A] toggle Animations      [Z] Back");

		System.out.print(MiscUtil.inputColor);
		MiscUtil.indent(4);
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);

		switch (userInput) {
		case "a":
			AccountService.toggleAnimationSetting();
			settings();
			break;
		case "A":
			AccountService.toggleAnimationSetting();
			settings();
			break;
		case "z":
			mainMenu();
			break;
		case "Z":
			mainMenu();
			break;
		}

	}

	// Allow the user to view their balance
	private static void accountBalance() throws InterruptedException {
		System.out.println('\n' + "  ");
		MiscUtil.indent();
		AnimationUtility.typingByLetter("ACCOUNT SUMMARY", 32, 0);
		System.out.println('\n' + "  __________________________");
		System.out.println("  Current Balance: " + MiscUtil.df2(AccountService.selectedAccount.getBalance()));
		System.out.println('\n');
		try {
			// no global animation timer input because ACCOUNT BALANCE looks very jarring
			// without the delay
			TimeUnit.MILLISECONDS.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AnimationUtility.typingByWord("  What else can we help you with?", 120, 20);
		mainMenu();
	}

	// Allow the user to withdraw funds:
	private static void withdraw() throws InterruptedException {
		System.out.println("      [A] Account balance      [Z] Back" + '\n');

		MiscUtil.indent();
		AnimationUtility.typingByWord("Enter the amount to withdraw:", 80, 5);

		MiscUtil.indent(4);
		System.out.print(MiscUtil.inputColor + "$");
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);

		switch (userInput) {
		case "a":
			AnimationUtility.typingByWord("Current Balance: $" + AccountService.selectedAccount.getBalance(), 80, 10);
			try {
				MiscUtil.indent(4);
				System.out.print(MiscUtil.inputColor + "$");
				userInput = sc.nextLine();
				System.out.print(MiscUtil.RESET);
				
				AccountService.withdrawTransaction(Double.parseDouble(userInput));
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(
						MiscUtil.WARNING + "  YOU MUST ENTER A POSITIVE AMOUNT TO WITHDRAW" + '\n' + MiscUtil.RESET, 90, 10);
//				System.out.println(MiscUtil.WARNING + "The withdrawal amount must be positive." + MiscUtil.RESET);
				withdraw();
				e.printStackTrace();
			} catch (BalanceLimitException e2) {
				MiscUtil.indent(6);
				AnimationUtility.typingCustomSplitter(
						MiscUtil.WARNING + "~~ @IN@SUF@FIC@IANT@ FUNDS@ ~~" + MiscUtil.RESET, 200, 50);
				withdraw();
				e2.printStackTrace();
			} catch (NumberFormatException e3) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + "Enter a number" + MiscUtil.RESET, 120, 20);
				withdraw();
				e3.printStackTrace();
			}
			AccountHome.mainMenu();
			break;
		case "A":
			AnimationUtility.typingByWord("Current Balance: $" + AccountService.selectedAccount.getBalance() + '\n', 80, 10);
			try {
				MiscUtil.indent(4);
				System.out.print(MiscUtil.inputColor + "$");
				userInput = sc.nextLine();
				System.out.print(MiscUtil.RESET);
				
				AccountService.withdrawTransaction(Double.parseDouble(userInput));
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(
						MiscUtil.WARNING + "YOU MUST ENTER A POSITIVE AMOUNT TO WITHDRAW" + MiscUtil.RESET, 90, 10);
				withdraw();
//				e.printStackTrace();
			} catch (BalanceLimitException e2) {
				MiscUtil.indent(6);
				AnimationUtility.typingCustomSplitter(
						MiscUtil.WARNING + "~~ @IN@SUF@FIC@IANT@ FUNDS@ ~~" + MiscUtil.RESET, 200, 50);
				withdraw();
//				e2.printStackTrace();
			} catch (NumberFormatException e3) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + "Enter a number" + MiscUtil.RESET, 120, 20);
				withdraw();
//				e3.printStackTrace();
			}
			AccountHome.mainMenu();
			break;
		case "z":
			mainMenu();
			break;
		case "Z":
			mainMenu();
			break;
		default:
			try {
				AccountService.withdrawTransaction(Double.parseDouble(userInput));
				AccountHome.mainMenu();
				break;
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + '\n' + 
						"YOU MUST ENTER A POSITIVE AMOUNT TO WITHDRAW." + '\n' + MiscUtil.RESET, 90, 10);
//				e.printStackTrace();
				withdraw();
			} catch (BalanceLimitException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingCustomSplitter(
						MiscUtil.WARNING + "~~ @IN@SUF@FIC@IANT@ FUNDS@ ~~" + MiscUtil.RESET, 200, 50);
				withdraw();
			} catch (NumberFormatException e) {
				MiscUtil.indent(8);
				AnimationUtility.typingByLetter(MiscUtil.WARNING + "Invalid Entry" +'\n' + MiscUtil.RESET, 100, 20);
				withdraw();
//				e.printStackTrace();
			}
			AccountHome.mainMenu();
			break;
		}
	}

	// Allow the user to make a deposit
	private static void deposit() throws NumberFormatException, InterruptedException {
		MiscUtil.indent();
		AnimationUtility.typingByWord("How much will you be depositing?", 60, 0);

		// green dollar sign and userInput
		MiscUtil.indent(4);
		System.out.print(MiscUtil.inputColor + "$");
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);

		switch (userInput) {
		case "a":
			AnimationUtility.typingByLetter("Current Balance: $" + AccountService.selectedAccount.getBalance(), 32, 0);

			// green dollar sign and userInput
			MiscUtil.indent();
			System.out.print(MiscUtil.inputColor + "$");
			userInput = sc.nextLine();
			System.out.print(MiscUtil.RESET);
			try {
				AccountService.depositTransaction(Double.parseDouble(userInput));
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + '\n' + 
						"YOU MUST ENTER A POSITIVE AMOUNT TO DEPOSIT." + '\n' + MiscUtil.RESET, 90, 10);
//				e.printStackTrace();
				deposit();
			}
			break;
		case "A":
			AnimationUtility.typingByLetter("Current Balance: $" + AccountService.selectedAccount.getBalance(), 32, 0);

			// green dollar sign and userInput
			MiscUtil.indent();
			System.out.print(MiscUtil.inputColor + "$");
			userInput = sc.nextLine();
			System.out.print(MiscUtil.RESET);
			try {
				AccountService.depositTransaction(Double.parseDouble(userInput));
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + '\n' + 
						"YOU MUST ENTER A POSITIVE AMOUNT TO DEPOSIT." + '\n' + MiscUtil.RESET, 90, 10);
//				e.printStackTrace();
				deposit();
			}
			break;
		default:
			try {
				AccountService.depositTransaction(Double.parseDouble(userInput));
			} catch (NegativeTransactionException e) {
				MiscUtil.indent(4);
				AnimationUtility.typingByWord(MiscUtil.WARNING + '\n' +
						"YOU MUST ENTER A POSITIVE AMOUNT TO DEPOSIT." + '\n' + MiscUtil.RESET, 90, 10);
//				e.printStackTrace();
				deposit();
			} catch (NumberFormatException e) {
				MiscUtil.indent(8);
				AnimationUtility.typingByLetter(MiscUtil.WARNING + "Invalid Entry" +'\n' + MiscUtil.RESET, 100, 20);
				deposit();
//				e.printStackTrace();
			}
			break;
		}
		AccountHome.mainMenu();
//		int depositAmount = sc.nextLine();
	}

	private static void confirmLogout() throws InterruptedException {
		AnimationUtility.typingByWord("     Are you sure you wish to leave?", 60, 0);
		AnimationUtility.typingByWord("       [S] STAY         [X] LOGOUT", 60, 0);
		System.out.print(MiscUtil.inputColor);
		MiscUtil.indent(4);
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);
		switch (userInput) {
		case "s":
			System.out.print("           ");
			AnimationUtility.typingByLetter("-Logout aborted-" + '\n', 30, 0);
			mainMenu();
			break;
		case "S":
			System.out.print("           ");
			AnimationUtility.typingByLetter("-Logout aborted-" + '\n', 30, 0);
			mainMenu();
			break;
		case "x":
			System.out.println("  ______________________________________________" + '\n');
			AnimationUtility.typingCustomSplitter("    *@~@*@~@*@~@ Thank@ you@ for@ choosing@ PZero @~@*@~@*@~*", 45,
					0);
			System.out.println("  ______________________________________________" + '\n' + '\n');
			LoginScreen.blankLogin();
			break;
		case "X":
			System.out.println("  ______________________________________________" + '\n');
			AnimationUtility.typingCustomSplitter("    *@~@*@~@*@~@ Thank@ you@ for@ choosing@ PZero @~@*@~@*@~*", 45,
					0);
			System.out.println("  ______________________________________________" + '\n' + '\n');
			LoginScreen.blankLogin();
			break;
		}
	}

}
