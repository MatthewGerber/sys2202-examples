package sys2202.objects;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LifeWithoutObjects {

	public static void main(String[] args) throws Exception {

		// let's first see a painful way of structuring our data. create an accelerometer reading of <1,2,3>
		ArrayList<Double> accelerationVector = new ArrayList<Double>();
		accelerationVector.add(1.0);  // X 
		accelerationVector.add(2.0);  // Y   we'll have to keep track of which indices hold which acceleration vector elements 
		accelerationVector.add(3.0);  // Z
		
		// we cannot refer to elements by name, which is a pain.
		System.out.println("Y acceleration:  " + accelerationVector.get(1));  
		
		// what about time and the device ID? we cannot add them to the list.
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
	    // accelerationVector.add(timestamp); // ERROR
		
		// okay fine. let's bundle our acceleration reading together with the timestamp in a new type of array list.
		ArrayList<Object> accelerometerDatumAndTimestamp = new ArrayList<Object>();
		accelerometerDatumAndTimestamp.add(accelerationVector);
		accelerometerDatumAndTimestamp.add(timestamp);
		
		// print the Y acceleration element -- what an ugly mess!
		Double yAcceleration = ((ArrayList<Double>) accelerometerDatumAndTimestamp.get(0)).get(1);
		System.out.println("Y acceleration:  " + yAcceleration);
	}
}