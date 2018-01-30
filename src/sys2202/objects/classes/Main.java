package sys2202.objects.classes;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {
		
		String deviceId = "234-2-432-r-32-23";
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
		
		AccelerometerDatum accelerometerDatum = new AccelerometerDatum(deviceId, timestamp, 1, 2, 3);
		System.out.println(accelerometerDatum.toString());
		
		// can't change private fields
	    // accelerometerDatum.deviceId = "2342-234-32-3";   // ERROR
		// accelerometerDatum.setDeviceId("2342-234-32-3"); // ERROR

	}
}