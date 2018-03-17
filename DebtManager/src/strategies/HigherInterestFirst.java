package strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import models.Debt;

public class HigherInterestFirst extends HigherBalanceFirst{

	public HigherInterestFirst(float budget) {
		super(budget);
		// TODO Auto-generated constructor stub
	}
	public void addDebt(Debt d) {
		debts.add(d);
		sortDebts();
	}
	
	public void sortDebts() {
		Collections.sort(debts, new InterestComparator());
	}
	
	private class InterestComparator implements Comparator<Debt> {

		@Override
		public int compare(Debt debt1, Debt debt2) {
			// TODO Auto-generated method stub
			if(debt1.getAPR()<=debt2.getAPR()) return 1;
			return -1;
		}
		
	}
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return "Higher Interest First";
	}
	
	
	public static void main(String[] args) {
		Debt card1, card2;
		card1 = new Debt("Chase Card",1014.6f, 22.74f, 50f, 1000);
		card2 = new Debt("Discover Card",2140.97f, 17.49f, 55f, 2000);
		System.out.println("What is your budget?");
		Scanner in = new Scanner(System.in);
		float budget = in.nextFloat();
		in.nextLine();
		in.close();
		HigherBalanceFirst hb = new HigherInterestFirst(budget);
		hb.addDebt(card1);
		hb.addDebt(card2);
		

		hb.simulatePayments();
		System.out.println("\tTotal Months: "+hb.getMonths());
		System.out.println("\tTotal Interest Charged: "+hb.getInterest());

	}

	

}
