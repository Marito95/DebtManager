package strategies;

import java.util.ArrayList;

import models.Debt;

public class MinPaymentsOnly extends DoublePayments{

	public ArrayList<Float> getThisMonthPayment(ArrayList<Debt> debts) {
		ArrayList<Float> payments = new ArrayList<>();
		
		for(int index = 0; index<debts.size(); index++) {
			payments.add(debts.get(index).getMinPayment());
			budget -= payments.get(index);
		}
		
		return payments;
	}
	
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return "Minimum Payments Only";
	}

	public static void main(String[] args) {
		Debt card1, card2;
		card1 = new Debt("Chase Card", 10800f, 7.79f, 219f, 15000);
//		card2 = new Debt("Discover Card",2140.97f, 17.49f, 55f, 2000);
		HigherBalanceFirst hb = new MinPaymentsOnly();
		hb.addDebt(card1);
//		hb.addDebt(card2);
		

		hb.simulatePayments();
		System.out.println("\tTotal Months: "+hb.getMonths());
		System.out.println("\tTotal Interest Charged: "+hb.getInterest());
		

	}

}
