package sys2202.phtis;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {

		String deviceId = "234-2-432-r-32-23";
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
		
		// can't instantiate abstract classes
		// Datum datum = new Datum(deviceId, timestamp); // ERROR
		
		Datum accelerometerDatum = new AccelerometerDatum(deviceId, timestamp, 1, 2, 3);
		System.out.println(accelerometerDatum.toString());
		
		// polymorphism handles varying behaviors of derived classes.
		Datum locationDatum = new LocationDatum(deviceId, timestamp, 35, -20, 10);
		System.out.println(locationDatum.toString());
	}
}