package models;

public class Debt {
	String name;
	private float apr, balance, minPayment, limit;
	
	public Debt(String name, float balance, float apr, float minPayment, float limit) {
		this.balance = balance;
		this.apr = apr;
		this.minPayment = minPayment;
		this.name = name;
		this.limit = limit;
		printClassName();
	}
	
	public Debt getClone() {
		return new Debt(name, balance, apr, minPayment, limit);
	}
	
	public float getBalance() {
		return balance;
	}
	public float getAPR() {
		return apr;
	}
	
	public float getMinPayment() {
		return this.minPayment;
	}
	
	public void setMinPayment(float min) {
		this.minPayment = min;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBalance(float balance) {
		if(balance>0)
		this.balance = balance;
		else this.balance = 0;
	}
	
	public float getInterestCharge() {
		return this.balance*this.apr/12/100;
	}
	
	public float getLimit() {
		return limit;
	}
	
	public static void printClassName() {
		System.out.println("Debt");
	}
	
	public void printClassName2() {
		System.out.println("Debt1");
	}
	

}
