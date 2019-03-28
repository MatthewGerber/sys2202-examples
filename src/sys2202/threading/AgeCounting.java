package sys2202.threading;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class AgeCounting {

	public static void main(String[] args) 
	{
		// create a list of 100 million people randomly aged from 1-100
		System.out.println("Creating list of people...");
		ArrayList<Integer> ages = new ArrayList<Integer>(100000000);
		Random r = new Random();
		for (int i = 1; i <= 100000000; ++i) 
		{
			int age = r.nextInt(100) + 1;
			ages.add(age);
		}

		// time how long it takes to count the number of people with each age (i.e., the
		// age histogram). note that this implementation is intentionally written to be
		// inefficient in order to demonstrate the concepts being covered in class. it
		// is worth considering what a more efficient approach might be...
		System.out.println("Getting age counts");

		long startTime = System.currentTimeMillis();

		Hashtable<Integer, Integer> ageCount = new Hashtable<Integer, Integer>();
		for (int age = 1; age <= 100; ++age) 
		{
			System.out.println("..." + age);
			int count = HowManyPeopleHaveAge(ages, age);
			ageCount.put(age, count);
		}
		
		System.out.println();

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println("Done! Age counting took " + elapsedTime + "ms.");
	}

	private static int HowManyPeopleHaveAge(ArrayList<Integer> ages, int age) 
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