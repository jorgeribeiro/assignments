package assignment9;

public abstract class Handler {
	protected Handler successor;
	
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	public abstract void processRequest(Customer customer);
}

class ProcessCredit extends Handler {
	@Override
	public void processRequest(Customer customer) {
		System.out.println(">> Processing credit...");
		switch(Util.randomInt()) { // randoms a type of credit
		case 1 :
			customer.setTypeOfCredit(Credit.ADEQUATE);
			System.out.println(">> Customer ID " + customer.getID() + " has adequate credit.");
			break;
		case 2 :
			customer.setTypeOfCredit(Credit.GOOD);
			System.out.println(">> Customer ID " + customer.getID() + " has good credit.");
			break;
		case 3 :
			customer.setTypeOfCredit(Credit.EXCELLENT);
			System.out.println(">> Customer ID " + customer.getID() + " has excellent credit.");
			break;
		case 4 :
			customer.setTypeOfCredit(Credit.INADEQUATE);
			System.out.println(">> Customer ID " + customer.getID() + " has inadequate credit.");
			break;
		}
		successor.processRequest(customer);
	}
}

class ProcessMailing extends Handler {
	
	@Override
	public void processRequest(Customer customer) {
		System.out.println(">> Processing mail...");
		if(customer.getTypeOfCredit() == Credit.ADEQUATE)
			customer.setTypeOfMail(Mail.REGULAR);
		else if(customer.getTypeOfCredit() == Credit.GOOD)
			customer.setTypeOfMail(Mail.PRIORITY);
		else if(customer.getTypeOfCredit() == Credit.EXCELLENT)
			customer.setTypeOfMail(Mail.EXPRESS);
		else
			customer.setTypeOfMail(Mail.REJECTED);
	}
}
