package strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import models.Debt;

public class HigherDamageFirst extends HigherBalanceFirst{
	

	public HigherDamageFirst(float budget) {
		super(budget);
		// TODO Auto-generated constructor stub
	}
	public void addDebt(Debt d) {
		debts.add(d);
		sortDebts();
	}
	
	public void sortDebts() {
		Collections.sort(debts, new DamageComparator());
	}
	
	private void sortDebts(ArrayList<Debt> d) {
		Collections.sort(d, new DamageComparator());

	}
	
	
	public ArrayList<Float> getThisMonthPayment(ArrayList<Debt> debts) {
		sortDebts(debts);
		ArrayList<Float> payments = new ArrayList<>();
		
		for(int index = 1; index<debts.size(); index++) {
			payments.add(debts.get(index).getMinPayment());
			budget -= payments.get(index-1);
		}
		
		payments.add(0, budget);
		return payments;
	}
	
	private class DamageComparator implements Comparator<Debt> {

		@Override
		public int compare(Debt debt1, Debt debt2) {
			// TODO Auto-generated method stub
			if(debt1.getBalance()/debt1.getLimit()<=debt2.getBalance()/debt2.getLimit()) return 1;
			return -1;
		}
		
	}
	
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return "Higher Damage First";
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
		HigherBalanceFirst hb = new HigherDamageFirst(budget);
		hb.addDebt(card1);
		hb.addDebt(card2);
		

		hb.simulatePayments();
		System.out.println("\tTotal Months: "+hb.getMonths());
		System.out.println("\tTotal Interest Charged: "+hb.getInterest());

	}

}
