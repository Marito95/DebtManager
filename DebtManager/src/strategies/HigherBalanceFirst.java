package strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import models.Debt;

public class HigherBalanceFirst implements Strategy {
	ArrayList<Debt> debts;
	float budget, interest, initialBudget, goal;
	int months;
	
	public void setGoal(float g) {
		this.goal = g;
	}
	
	public float getGoal() {
		return goal;
	}
	public HigherBalanceFirst(float budget){
		debts = new ArrayList<>();
		this.budget = initialBudget = budget;
	}
	
	
	public void addDebt(Debt d) {
		debts.add(d);
		sortDebts();
	}
	
	public ArrayList<Float> getThisMonthPayment(ArrayList<Debt> debts) {
		ArrayList<Float> payments = new ArrayList<>();
		
		for(int index = 1; index<debts.size(); index++) {
			payments.add(debts.get(index).getMinPayment());
			budget -= payments.get(index-1);
		}
		
		payments.add(0, budget);
		return payments;
	}
	
	public int simulatePayments(){
		ArrayList<Debt> cloneDebts = new ArrayList<>();
		HashMap<Integer, ArrayList<Float>> simulation = new HashMap<>();
		ArrayList<Float> payments;

		for(Debt d: debts) {
			cloneDebts.add(d.getClone());
		}
		
		
		int index=0;
		
		boolean end = false;
		while(!end) {
						
			payments = getThisMonthPayment(cloneDebts);
			
			simulation.put(index, payments);
//			System.out.println("Month "+(index+1));
			
			for(int i=0; i<cloneDebts.size(); i++) {
				Debt d = cloneDebts.get(i);
				float interest = d.getInterestCharge();
				d.setBalance(d.getBalance()-payments.get(i)+d.getInterestCharge());
//				System.out.println("\t"+d.getName());
//				System.out.println("\t\tExpected Balance: " + d.getBalance());
//				System.out.println("\t\tInterest Charged "+interest);
				this.interest+=interest;
				if(d.getBalance()==0) {
					cloneDebts.remove(i);
				}
				

			}
			budget = initialBudget;
			index++;
			
			
			end = cloneDebts.size()==0;
			
			
		}
		setMonths(index);
		return index;
	}
	
	public int simulateUntilGoal() {
		ArrayList<Debt> cloneDebts = new ArrayList<>();
		HashMap<Integer, ArrayList<Float>> simulation = new HashMap<>();
		ArrayList<Float> payments;

		for(Debt d: debts) {
			cloneDebts.add(d.getClone());
		}
		
		
		int index=0;
		
		boolean end = false;
		while(!end) {
						
			payments = getThisMonthPayment(cloneDebts);
			
			simulation.put(index, payments);
//			System.out.println("Month "+(index+1));
			
			for(int i=0; i<cloneDebts.size(); i++) {
				Debt d = cloneDebts.get(i);
				float interest = d.getInterestCharge();
				d.setBalance(d.getBalance()-payments.get(i)+d.getInterestCharge());
//				System.out.println("\t"+d.getName());
//				System.out.println("\t\tExpected Balance: " + d.getBalance());
//				System.out.println("\t\tInterest Charged "+interest);
				this.interest+=interest;
				if(d.getBalance()<=debts.get(i).getBalance()*goal) {
					cloneDebts.remove(i);
				}
				

			}
			budget = initialBudget;
			index++;
			
			
			end = cloneDebts.size()==0;
			
			
		}
		setMonths(index);
		return index;
	}

	
	public void setMonths(int months) {
		this.months = months;
		
	}
	
	public int getMonths() {
		return months;
		
	}
	
	public void setTotalInterest(float interest) {
		this.interest = interest;
		
	}
	
	public float getInterest() {
		return interest;
	}
	
	public void sortDebts() {
		Collections.sort(debts, new BalanceComparator());
	}
	
	
	private class BalanceComparator implements Comparator<Debt> {

		@Override
		public int compare(Debt debt1, Debt debt2) {
			// TODO Auto-generated method stub
			if(debt1.getBalance()<=debt2.getBalance()) return 1;
			return -1;
		}
		
	}
	
	@Override
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return "Higher Balance First";
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
		
		HigherBalanceFirst hb = new HigherBalanceFirst(budget);
		hb.addDebt(card1);
		hb.addDebt(card2);
		
		hb.setGoal(.30f);
		hb.simulateUntilGoal();
		System.out.println("\tTotal Months: "+hb.getMonths());
		System.out.println("\tTotal Interest Charged: "+hb.getInterest());

	}


	
}
