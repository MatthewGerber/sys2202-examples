package sys2202.threading;

import java.util.ArrayList;
import java.util.Hashtable;

public class AgeCountRunner implements Runnable 
{
	private ArrayList<Integer> ages;
	private int startAge;
	private int endAge;
	private Hashtable<Integer, Integer> ageCount;
	
	public Hashtable<Integer, Integer> GetAgeCount()
	{
		return ageCount;
	}
	
	public AgeCountRunner(ArrayList<Integer> ages, int startAge, int endAge)
	{
		this.ages = ages;
		this.startAge = startAge;
		this.endAge = endAge;
	}

	@Override
	public void run() {
		
		System.out.println("Counting people aged " + startAge + "-" + endAge);
		
		this.ageCount = new Hashtable<Integer, Integer>();

		for(int age = startAge; age <= endAge; ++age)
		{
			int count = HowManyPeopleHaveAge(this.ages, age);
		    this.ageCount.put(age,  count);
		}
		
		System.out.println("Done counting people aged " + startAge + "-" + endAge + "...");
	}
	
	private int HowManyPeopleHaveAge(ArrayList<Integer> ages, int age)
	{
		int count = 0;
		
		for (int i = 0; i < ages.size(); ++i) 
		{
			if (ages.get(i) == age) 
			{
				count = count + 1;
			}
		}
		
		return count;
	}
}