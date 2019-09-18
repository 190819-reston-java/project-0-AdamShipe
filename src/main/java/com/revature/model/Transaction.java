package com.revature.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.revature.utilities.MiscUtil;

public class Transaction {

	private int id;
	private int accountId;
	private String shortDesc;
	private String detailDesc;
	private String dateTime;
	private double value;

	public Transaction(int id, int accountId, String shortDesc, String detailDesc, String dateTime, double d) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.shortDesc = shortDesc;
		this.detailDesc = detailDesc;
		this.dateTime = dateTime;
		this.value = d;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountId=" + accountId + ", shortDesc=" + shortDesc + ", detailDesc="
				+ detailDesc + ", dateTime=" + dateTime + ", value=" + value + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

//}

	private static String[] genBriefDesc = { "Account Deposit", "Account Withdrawal", "Check #" + (int)(Math.random()*1000), "Walmart", 
			"Amazon", "Grocery Kingdom", "Home Depot",
			"iTunes", "McDonalds", "Burger King", "Subway", "Target", "Groceryland", "Planet Groceries", "Planet Haircut",
			"Staples", "Costco", "Taco Bell", "Pizza Hut", "Rite-Aid", "Walgreens", "Dennys", "Nintendo",
			"LEGO", "Sleepys", "Samsung", "Apple", "Farmers Market", "Online purchase: ", "Drone Delivery: ",
			"Random Givaway (Charged to account)"
			};
	
	private static String[] genDetailDesc1 = {"Shopping", "Delivery", "Credit", "Subscribed", "Interactive",
			"Recurring", "Offline", "Black Market", "Dark Web", "Deep Web", "High Explosive",
			"Shopping Cart", "All-new", "2019", "2000", "2020-model", "Student", "Illegal", "Flame-retardant",
			"Flagged-as-questionable", "randomly-generated", "hard-to-find", "Titanium", "Flaming", "Addictive",
			"Dangerous", "Lifetime Supply", "Self-replenishing", "fun-size", "family-size", "Huge", "Perpetually-Expanding",
			"Hazardous", "Volatile", "Exotic", "Medicinal", "Twice-baked", "Slow-cooked", "3D",
			"4D", "5D","Six-pack", "6 month", "102 pack", "inflatable", "self-inflating", "Defrosted", "make-your-own",
			"reinforced", "Half-eaten", "Anti-gravity", "Thousand year old",
	};
	
	private static String[] genDetailDesc2 = { "Package", "Fun-set", "Starter Set", "Credit Card XXXXXXXXXXXX" + (int)(Math.random()*10000),
			"Credit Card **************" + (int)(Math.random()*100), "Action Figure","Poseable Figurine", "Fishing Lure",
			"Account#" + (int)(Math.random()*1000000), "Reference: #" + (int)(Math.random()*1000000000), "meal for 2", "instant breafast",
			"instant hydration", "Starter-kit", "Trip for 2", "Entrance fee", "Flu Shot",
			"Admit 1", "Hot Meal", "Power Tools", "Helicopter Ride", "Horseback-riding adventure", "Sandwich", "Burgers and Fries" 			
	};
	
	private static String[] post = {"as seen on TV!", "*Assembly Required", "*keep away from animals and small children",
			"*minor wear and tear", "serves 14", "do-it-yourself kit", "featuring U2", "starring Meryl Streep", "On-ice",
			"Trial Period", "*May Contain Nut Traces", "2019 edition", "Choking Hazard! Small Parts", "with Almond flavoring",
			"*Nuts and bolts sold separately", "karaoke edition", "*no refunds under any circumstances", "*Lifetime Supply",
			"Free refills", "*pending investigation", "*some assembly required"
			
	};
	
	
	
//	public static void genTransaction() {
//		String transactionDesc = genBriefDesc[(int)(Math.random()*genBriefDesc.length)];
//		String transactionDesc2 = new String();
//			if(Math.random()>0.5) {
//				transactionDesc2=genDetailDesc1[(int)(Math.random()*genDetailDesc1.length)]+" " +genDetailDesc2[(int)(Math.random()*genDetailDesc2.length)];
//			} else {
//				transactionDesc2=genDetailDesc2[(int)(Math.random()*genDetailDesc2.length)];
//			}
//		System.out.println(transactionDesc+" "+transactionDesc2);
//	}
	
	public static String gt() {
		int s1=(int) (Math.random()*genBriefDesc.length);
		String rGTransaction=genBriefDesc[s1];
		if (s1<3) {
			rGTransaction ="'" + rGTransaction + " #" + (int) (Math.random()*100000) + "'" +", ";
		} else {
			if(Math.random()>0.5) {
				rGTransaction=rGTransaction + " " + genDetailDesc1[(int)(Math.random()*genDetailDesc1.length)]+" " +genDetailDesc2[(int)(Math.random()*genDetailDesc2.length)];
			} else {
				rGTransaction=rGTransaction + " " + genDetailDesc2[(int)(Math.random()*genDetailDesc2.length)];
			}
			if(Math.random()>0.5) {
				rGTransaction=rGTransaction + " " + post[(int)(Math.random()*post.length)] + "'";
			} else {
				rGTransaction=rGTransaction + "'";
			}
		}
		return rGTransaction;
	}
	
	public static String lineGenerator() {
		return "(DEFAULT, "+(int)(Math.random()*14)+", \'"+gt() + ","+ "\'"+generateDate()+"\'" + ","
				+ MiscUtil.df.format(Math.random()*-100*(Math.random()))+")";
	}
	
	
//private String transaction = new String();
//Double amount;
//int code;
//String date;
//float datex;
//
//
//public Transaction() {
//	transaction = label[(int) (Math.random()*label.length)];
////	Date d1 = DateTimeFormatter;
////	Date d2 = new java.util.Date((int)(2019-Math.random()*2),(int)(12*Math.random()), (int)(29*Math.random()));
//	date=generateDate();
////	date = Date(5000);
////	setTransaction("hi there");
//}
//
static String generateDate() {
	int year = (int)(2020-Math.random()*2);
	int month = (int)(11*Math.random())+1;
	int day = (int)(27*Math.random())+1;
	if (month == 0 || day == 0 || year >= 2019 && month >= 9) {
		generateDate();
	}
	return month + "/" + day + "/" + year;
}
//
//
//public String getTransaction() {
//	return transaction;
//}
//
//
//public void setTransaction(String transaction) {
//	this.transaction = transaction;
//}
//
//
//public String getDate() {
//	return date;
//}
//
//
//public void setDate(String date) {
//	this.date = date;
//}

}