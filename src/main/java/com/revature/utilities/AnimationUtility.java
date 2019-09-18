package com.revature.utilities;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AnimationUtility {
	
	static public int animationToggle = 1;
	
	//Letter-by-letter animation
	public static void typingByLetter(String s, int milliseconds, int acceleration) {
//		milliseconds = 0;
//		acceleration = 0;
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
	
	//Word-by-word
	public static void typingByWord(String s, int milliseconds, int acceleration) {
//		milliseconds = 0;
//		acceleration = 0;
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
	
	//Custom Splitter : splits between the @
	public static void typingCustomSplitter(String s, int milliseconds, int acceleration) {
//		milliseconds = 0;
//		acceleration = 0;
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
