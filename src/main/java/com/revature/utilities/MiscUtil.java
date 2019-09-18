package com.revature.utilities;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.revature.service.AccountService;

public class MiscUtil {
	
	public static Logger logger = Logger.getLogger(AccountService.class);

	public final static String WARNING = "\033[1;91m";
	public static String successColor = "\033[4;32m";
	public static String inputColor = "\033[0;94m";
	public final static String RESET = "\u001B[0m";
	public final static String DISABLED_COLOR = "\033[1;90m"; // bold white text
	public final static String WHITE_TEXT = "\033[1;37m";
//	public final static String ROWS = "\033[0;106m";	//Bright cyan BG
	public final static String ROWS = "\033[0;100m"; // Grey BG

	public static DecimalFormat df = new DecimalFormat("#,###.##");
	

	// my own formatting solution, this got a little convoluted
	// adds zeros and dollar signs
	public static String df2(double d, int i) {
		String result = df.format(Math.abs(d));
		String str = "$" + String.valueOf(result);
		if (str.length() == str.indexOf('.')) {
		} else if (str.length() == str.indexOf('.') + 2) {
			StringBuffer sb = new StringBuffer();
			sb.append("$");
			sb.append(result);
			sb.append("0");
			String s = sb.toString();
			str = sb.toString();
		} else if (str.length() == str.indexOf('.') + 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("$");
			sb.append(result);
			sb.append("0");
			String s = sb.toString();
			str = sb.toString();
		} else if (str.indexOf('.') == -1) {
			StringBuffer sb = new StringBuffer();
			sb.append("$");
			sb.append(result);
			sb.append(".00");
			String s = sb.toString();
			;
			str = sb.toString();
		}
		if(d<0) {
			str="-"+str;
		}
		if (str.length() < i) {
			for (int j = i - str.length(); i < str.length(); i++) {
				str = " " + str;
			}
		}
		if (d > 0) {
			str = " " + str;
		}
		return str;
	}
	
		public static String df2(Double d) {
		return df2(d, 6);
	}
		
	//front padding, self-explanatory
	public static String frontPadding(String str, char c, int i) {
		while(str.length()<i) {
			str=c + str;
		}
		return str;	
	}




	public static void indent(int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(" ");
		}
	}

	public static void indent() {
		indent(2);
	}

	//for formatting text
	//when the transaction table overflows, the text will be CLEANLY INTERRU...
	//	and when the text DOESNT go far enough,
	//	it will get padded so the amount column is aligned
	public static String fixLength(String initial, int length, char z, char c, int e) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(initial);
		String str = null;
		if (stringBuffer.length() < length) {
			for (int i = initial.length(); i < length; i++) {
				stringBuffer.append(c);
			}
			str = stringBuffer.toString();
		} else if (initial.length() > length) {
			StringBuffer x = new StringBuffer();
			x.append(initial.substring(0, length - e));
			for (int i = x.length(); i < length; i++) {
				x.append(z);
			}
			str = x.toString();
		} else {
			str = initial;
		}

		return str;
	}

	public static String fixLength(String initial, int length, char c, char z) {
		return fixLength(initial, length, c, z, 3);
	}

	public static String fixLength(String initial, int length, char c) {
		return fixLength(initial, length, c, ' ');
	}

	public static String fixLength(String initial, int length) {
		return fixLength(initial, length, '.');
	}

}
