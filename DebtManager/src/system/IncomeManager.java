package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Income;
import strategies.HigherInterestFirst;
import strategies.Strategy;

public class IncomeManager {
	Map<Integer, ArrayList<Float>> incomeMap;
	
	public IncomeManager() {
		incomeMap = new HashMap<>();
	}
	
	
	public void addIncome(float q, int m) {
		Income i = new Income(q, m);
		if(!incomeMap.containsKey(m)) {
			incomeMap.put(m, new ArrayList<>());
		}
		incomeMap.get(m).add(q);
		
	}
	
	public float getIncome(int m) {
		float monthIncome = 0;
		if(incomeMap.get(m)!=null)
		for(Float q: incomeMap.get(m)) {
			monthIncome+=q;
		}
		
		return monthIncome;
	}
	
	public static void main(String[] args) {
		Strategy s = new HigherInterestFirst(500);
		IncomeManager im = new IncomeManager();
		im.addIncome(1150,1);
		im.addIncome(1150,2);
		im.addIncome(1150,3);
		im.addIncome(1000,3);
		im.addIncome(1150,4);
		im.addIncome(900,5);
		float totalIncome = 0;
		for(Integer i: im.incomeMap.keySet()) {
			totalIncome +=im.getIncome(i);
			totalIncome-=500;
		}
		System.out.println(totalIncome);
		
	}
	
	

}
