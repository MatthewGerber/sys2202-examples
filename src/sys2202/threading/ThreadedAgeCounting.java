package sys2202.threading;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class ThreadedAgeCounting {

	public static void main(String[] args) throws InterruptedException 
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

		// time how long it takes to count the number of people with each age
		System.out.println("Getting age counts...");

		long startTime = System.currentTimeMillis();

		// count people aged 1-12
		AgeCountRunner runner1 = new AgeCountRunner(ages, 1, 12);
		Thread thread1 = new Thread(runner1);
		thread1.start();
		
		// ...and so on for the other age groups
		AgeCountRunner runner2 = new AgeCountRunner(ages, 13, 24);
		Thread thread2 = new Thread(runner2);
		thread2.start();
		
		AgeCountRunner runner3 = new AgeCountRunner(ages, 25, 36);
		Thread thread3 = new Thread(runner3);
		thread3.start();
		
		AgeCountRunner runner4 = new AgeCountRunner(ages, 37, 48);
		Thread thread4 = new Thread(runner4);
		thread4.start();
		
		AgeCountRunner runner5 = new AgeCountRunner(ages, 49, 60);
		Thread thread5 = new Thread(runner5);
		thread5.start();
		
		AgeCountRunner runner6 = new AgeCountRunner(ages, 61, 72);
		Thread thread6 = new Thread(runner6);
		thread6.start();
		
		AgeCountRunner runner7 = new AgeCountRunner(ages, 73, 84);
		Thread thread7 = new Thread(runner7);
		thread7.start();
		
		AgeCountRunner runner8 = new AgeCountRunner(ages, 85, 100);
		Thread thread8 = new Thread(runner8);
		thread8.start();
		
		// wait for all count runners to finish
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
		thread7.join();
		thread8.join();
		
		// merge results from the individual count runners into a single merged hashtable
		System.out.println("Merging intermediate results...");
		Hashtable<Integer, Integer> mergedAgeCount = new Hashtable<Integer, Integer>();
		MergeCounts(runner1.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner2.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner3.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner4.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner5.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner6.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner7.GetAgeCount(), mergedAgeCount);
		MergeCounts(runner8.GetAgeCount(), mergedAgeCount);
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		
		System.out.println("Done! Age counting took " + elapsedTime + "ms.");
	}
	
	private static void MergeCounts(Hashtable<Integer, Integer> runnerAgeCount, Hashtable<Integer, Integer> mergedAgeCount)
	{
		for (int age : runnerAgeCount.keySet())
		{
			int count = runnerAgeCount.get(age);
			mergedAgeCount.put(age, count);
		}
	}
}