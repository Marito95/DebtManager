package strategies;

import java.util.ArrayList;

import models.Debt;

public class DoublePayments extends HigherBalanceFirst{

	DoublePayments(float budget) {
		super(budget);
		// TODO Auto-generated constructor stub
	}
	
	public DoublePayments(){
		super(0);
	}
	
	public ArrayList<Float> getThisMonthPayment(ArrayList<Debt> debts) {
		ArrayList<Float> payments = new ArrayList<>();
		
		for(int index = 0; index<debts.size(); index++) {
			payments.add(debts.get(index).getMinPayment()*2);
			budget -= payments.get(index);
		}
		
		return payments;
	}
	
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return "Double Payments";
	}

	public static void main(String[] args) {
		Debt card1, card2;
		card1 = new Debt("Chase Card", 10800f, 7.79f, 219f, 15000);
		HigherBalanceFirst hb = new DoublePayments();
		hb.addDebt(card1);
		hb.simulatePayments();
		System.out.println("\tTotal Months: "+hb.getMonths());
		System.out.println("\tTotal Interest Charged: "+hb.getInterest());
		

	}
}
