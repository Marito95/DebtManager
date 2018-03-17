package strategies;

import java.util.ArrayList;

import models.Debt;

public interface Strategy {
		
	
	public void addDebt(Debt d);
	
	public ArrayList<Float> getThisMonthPayment(ArrayList<Debt> debts);
	
	public int simulatePayments();
	
	
	public void sortDebts();
	
	public String getStrategyName();
	
	public void setMonths(int months);
	
	public int getMonths();
	
	public void setTotalInterest(float interest);
	
	public float getInterest();
	
	public void setGoal(float goal);
	
	public float getGoal();
	
	public int simulateUntilGoal();

}
