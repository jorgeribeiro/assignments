package assignment9;

public class Util {
	public static String printMail(Mail mail) {
		String toReturn = "";
		if(mail == Mail.REGULAR)
			toReturn = "regular mail";
		else if(mail == Mail.PRIORITY)
			toReturn = "priority mail";
		else if(mail == Mail.EXPRESS)
			toReturn = "express mail with a discount coupon";
		return toReturn;
	}
	
	public static int randomInt() {
		return ((int) (Math.random() * 4 + 1));
	}
}
