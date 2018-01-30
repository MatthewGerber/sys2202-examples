package sys2202.phtis;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {

		String deviceId = "asdfasdf";
		LocalDateTime timestamp = LocalDateTime.parse("2017-01-31T11:30");
		
		// can't instantiate abstract classes
		// Datum datum = new Datum(deviceId, timestamp); // ERROR
		
		double x = 1;
		double y = 2;
		double z = 3;
		
		Datum accelerometerDatum = new AccelerometerDatum(deviceId, timestamp, x, y, z);
		System.out.println(accelerometerDatum.toString());
		
		// polymorphism handles varying behaviors of derived classes. the toString method for LocationDatum objects
		// behaves differently from the toString method of AccelerometerDatum objects.
		double lat = 35;
		double lon = -20;
		double accuracy = 10;
		
		Datum locationDatum = new LocationDatum(deviceId, timestamp, lat, lon, accuracy);
		System.out.println(locationDatum.toString());
	}
}