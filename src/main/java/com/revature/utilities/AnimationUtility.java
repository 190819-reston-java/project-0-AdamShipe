package com.revature.utilities;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AnimationUtility {
	
	static public int animationToggle = 1;
	
	/**
	 * Letter-by-letter animation
	 * 
	 * @param s for the string to output
	 * @param milliseconds for the time to hold on each character
	 * @param acceleration for how much to speed up
	 * 			(or speed down) for each additional character
	 */
	public static void typingByLetter(String s, int milliseconds, int acceleration) {
		char[] banner = new char[s.length()];
		for(int i = 0; i<s.length(); i++) {
			banner[i] = s.charAt(i);
		}
		for (int i = 0; i<banner.length; i++) {
			System.out.print(banner[i]);
			try {
				TimeUnit.MILLISECONDS.sleep((milliseconds - acceleration*i)*animationToggle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Word-by-word animation: same as letter by letter
	 * 							except it goes word-by-word
	 * @param s
	 * @param milliseconds
	 * @param acceleration
	 */
	public static void typingByWord(String s, int milliseconds, int acceleration) {
		String[] banner = s.split(" ");
		for(String b : banner) {
			int i = 0;
			System.out.print(b + " ");
			i+=1;
			try {
				TimeUnit.MILLISECONDS.sleep((milliseconds - acceleration*i)*animationToggle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	/**
	 * Custom animation cutter
	 * 				pauses between the @ symbol
	 * @param s
	 * @param milliseconds
	 * @param acceleration
	 */
	public static void typingCustomSplitter(String s, int milliseconds, int acceleration) {
		String[] banner = s.split("@");
		for(String b : banner) {
			int i = 0;
			System.out.print(b);
			i+=1;
			try {
				TimeUnit.MILLISECONDS.sleep((milliseconds - acceleration*i)*animationToggle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}
}
