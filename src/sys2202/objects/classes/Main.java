package sys2202.objects.classes;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String deviceId = "asdfasdfasdf";
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
		double x = 1;
		double y = 2;
		double z = 3;
		
		AccelerometerDatum accelerometerDatum = new AccelerometerDatum(deviceId, timestamp, x, y, z);
		
		// with objects, we can refer to data fields/attributes by name
		System.out.println(accelerometerDatum.getY());
		
		// we can also protect ourselves from undesired field modifications
	    // accelerometerDatum.y = -5000;   // ERROR at compile time.

		// accelerometerDatum.setY(5000);	// Need to add the setY method, which will throw an exception at run time.
	}
}