package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import models.Debt;
import strategies.DoublePayments;
import strategies.EqualPayments;
import strategies.HigherBalanceFirst;
import strategies.HigherDamageFirst;
import strategies.HigherInterestFirst;
import strategies.MinPaymentsOnly;
import strategies.Strategy;

public class MainSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Debt.printClassName();
	
		Debt card1, card2;
		card1 = new Debt("Chase Card",1014.6f, 22.74f, 50f, 1000);
		card2 = new Debt("Discover Card",2105.54f, 17.49f, 55f, 2000);
		Debt.printClassName();
		
		System.out.println("What is your budget?");
		Scanner in = new Scanner(System.in);
		float b = in.nextFloat();
		in.nextLine();
		in.close();

		ArrayList<Strategy> strategies = new ArrayList<>();
		strategies.add(new MinPaymentsOnly());
		strategies.add(new DoublePayments());
		strategies.add(new EqualPayments(b));
		strategies.add(new HigherInterestFirst(b));
		strategies.add(new HigherDamageFirst(b));
		strategies.add(new HigherBalanceFirst(b));


		for(Strategy strategy: strategies) {
			strategy.addDebt(card1);
			strategy.addDebt(card2);
			strategy.setGoal(.30f);
			strategy.simulateUntilGoal();

		}
		Collections.sort(strategies, new MonthsComparator());
		System.out.println("Sorted by Months");
		System.out.println();
		for(Strategy strategy: strategies) {
			System.out.println(strategy.getStrategyName());

			System.out.println("\tTotal Months: "+strategy.getMonths());
			System.out.println("\tTotal Interest Charged: "+strategy.getInterest());
		}

		Collections.sort(strategies, new InterestComparator());
		System.out.println("Sorted by Interest");
		System.out.println();
		for(Strategy strategy: strategies) {
			System.out.println(strategy.getStrategyName());

			System.out.println("\tTotal Months: "+strategy.getMonths());
			System.out.println("\tTotal Interest Charged: "+strategy.getInterest());
		}


	}

	public  static class MonthsComparator implements Comparator<Strategy>{


		@Override
		public int compare(Strategy strat1, Strategy strat2) {
			// TODO Auto-generated method stub
			if(strat1.getMonths()<strat2.getMonths()) return -1;
			if(strat1.getMonths()==strat2.getMonths()) {
				if(strat1.getInterest()<=strat2.getInterest()) return -1;
				else return 1;

			}
			return 1;
		}

	}

	public  static class InterestComparator implements Comparator<Strategy>{


		@Override
		public int compare(Strategy strat1, Strategy strat2) {
			// TODO Auto-generated method stub
			if(strat1.getInterest()<strat2.getInterest()) return -1;
			if(strat1.getInterest()==strat2.getInterest()) {
				if(strat1.getMonths()<=strat2.getMonths()) return -1;
				else return 1;

			}
			return 1;
		}

	}



}
