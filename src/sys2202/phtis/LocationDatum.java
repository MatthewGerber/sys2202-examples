package sys2202.phtis;

import java.time.LocalDateTime;

public class LocationDatum extends Datum {

	private double latitude;
	private double longitude;
	private double accuracyInMeters;
	
	public double getLatitude() {
		
		return latitude;
	}
	
	public double getLongitude() {
		
		return longitude;
	}
	
	public double getAccuracyInMeters() {
		
		return accuracyInMeters;
	}
	
	public LocationDatum(String deviceId, LocalDateTime timestamp, double latitude, double longitude, double accuracyInMeters)
	{
		super(deviceId, timestamp);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracyInMeters = accuracyInMeters;
	}
	
	public Datum copy() {
		
		return new LocationDatum(getDeviceId(), getTimestamp(), latitude, longitude, accuracyInMeters);
	}
	
	public String toString() {
		
		return super.toString() + ", Lat:  " + latitude + ", Lon:  " + longitude + ", Accuracy (m):  " + accuracyInMeters;
	}
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof LocationDatum)) {
			return false;
		}
		
		LocationDatum location = (LocationDatum)o;
		return latitude == location.latitude && longitude == location.longitude;		
	}
}