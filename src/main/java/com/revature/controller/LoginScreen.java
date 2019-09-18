package com.revature.controller;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.revature.service.AccountService;
import com.revature.utilities.MiscUtil;
import com.revature.utilities.AnimationUtility;

public class LoginScreen {

	public static Scanner sc = new Scanner(System.in);
	
	private static AccountService accountService = new AccountService();
	
	//Count login attempts as a security measure
	static int attempts = 0;

	public static void blankLogin() throws InterruptedException {

		// Greet
		AnimationUtility.typingByLetter('\n' + "  WELCOME TO PZER0 BANKING" + '\n', 30, 4);
		System.out.println();
		try {
			TimeUnit.MILLISECONDS.sleep(400*AnimationUtility.animationToggle);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("To get started, first we need your login information");

		enterUserName();
	}

	static void enterUserName() throws InterruptedException {
		
//		System.out.println("To get started, enter your username and hit enter:");
		AnimationUtility.typingByWord("  To get started, enter your username and hit enter:", 50, 10);
//		System.out.print("Username: ");
		
		System.out.print("    " + MiscUtil.inputColor);
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);
		
		accountService.changeSelectedAccount(userInput);	
		
		if (accountService.getSelectedAccount() == null) {
			System.out.println("Invalid account name");
			enterUserName();
		} else {
//		System.out.println(accountService.getSelectedAccount());
		enterPassword();
		}
	}
	
	static void enterPassword() throws InterruptedException {
		
		System.out.println("  Now enter your password and hit enter:");

		System.out.print(MiscUtil.inputColor + "    ");
		String userInput = sc.nextLine();
		System.out.print(MiscUtil.RESET);
		if(userInput.equals(accountService.getSelectedAccount().getPassword())) {
			System.out.println(MiscUtil.successColor + "  Password accepted");
			TimeUnit.MILLISECONDS.sleep(400*AnimationUtility.animationToggle);

			MiscUtil.indent();
			MiscUtil.logger.debug("successful login on account " + AccountService.selectedAccount.getId() + "\u001B[0m");
			if(accountService.getSelectedAccount().getAnimationPref()==true) {
				AnimationUtility.animationToggle = 1;
			} else {
				AnimationUtility.animationToggle = 0;
			}
			AccountHome.accountWelcome();
			
		} else {
			System.out.println("Incorrect password");
			
//			AccountService.logger.debug("unsuccessful login attempt on account " + AccountService.selectedAccount.getId());

			//tick the attempt counter up
			attempts+=1;
			if(attempts < 5) {
			enterPassword();	
			} else {
				System.out.println(MiscUtil.WARNING);
				MiscUtil.logger.debug("excessive login attempts on account " + AccountService.selectedAccount.getId());
				
				//account lockdown
				System.out.println("\u001B[31m" + '\n' + "The account you are attempting to access has been locked down temporarily as a security measure.");
				System.out.println("Your location and biometric data have been submitted to over 100 different intelligence agencies.");
				System.out.println("Don't bother trying to run." + "\u001B[0m" + '\n');
				TimeUnit.MILLISECONDS.sleep(5000);
				blankLogin();
			}
		}
	}

}
