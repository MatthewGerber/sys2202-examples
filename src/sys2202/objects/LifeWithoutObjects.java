package sys2202.objects;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LifeWithoutObjects {

	public static void main(String[] args) throws Exception {

		// let's first see a painful way of structuring our data. create an accelerometer reading of <0,0,-1> (device lying on back)
		ArrayList<Float> acceleration = new ArrayList<Float>();
		acceleration.add(0f);  // X 
		acceleration.add(0f);  // Y   we'll have to keep track of which indices hold which acceleration vector values 
		acceleration.add(1f);  // Z
		
		// but wait, what about time and the device ID? we cannot add them to the list.
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
		// acceleration.add(timestamp); // ERROR
		
		// okay fine. let's bundle our acceleration reading together with the timestamp in a new array.
		ArrayList<Object> accelerometerDatum = new ArrayList<Object>();
		accelerometerDatum.add(acceleration);
		accelerometerDatum.add(timestamp);
		
		// print the datum timestamp -- what an ugly mess!
		System.out.println(accelerometerDatum.get(1).toString());
	}
}