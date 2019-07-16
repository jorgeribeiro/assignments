package assignment9;

public interface IVisitor {
	/** I first did the Visitor like follows:
	
	public void visit(AdequateCredit credit);
    public void visit(GoodCredit credit);
    public void visit(ExcellentCredit credit);
    public void visit(InadequateCredit credit);
    
    but I sent an email to you and you suggest me to
    did the method as below.
	**/
	public void visit(Customer customer);
}

class ProcessVisitor implements IVisitor {
	
	@Override
	public void visit(Customer customer) {
		System.out.println(">> Processing credit...");
		processCredit(customer);
		System.out.println(">> Processing mail");
		processMail(customer);
	}
	
	private void processCredit(Customer customer) {
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
	}
	
	private void processMail(Customer customer) {
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
