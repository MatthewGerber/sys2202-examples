package sys2202.objects.classes;

import java.time.LocalDateTime;

public class LocationDatum {

	private String deviceId;
	private LocalDateTime timestamp;
	private double latitude;
	private double longitude;
	private double accuracyInMeters;
	
	public String getDeviceId() {

		return deviceId;
	}

	public LocalDateTime getTimestamp() {

		return timestamp;
	}
	
	public double getLatitude() {
		
		return latitude;
	}
	
	public double getLongitude() {
		
		return longitude;
	}
	
	public double getAccuracyInMeters() {
		
		return accuracyInMeters;
	}
	
	public LocationDatum(String deviceId, LocalDateTime timestamp, double latitude, double longitude, double accuracyInMeters) {
		
		this.deviceId = deviceId;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracyInMeters = accuracyInMeters;
		
	}
	
	public String toString() {
		
		return "Device:  " + deviceId + ", Timestamp:  " + timestamp + ", Lat:  " + latitude + ", Lon:  " + longitude + ", Accuracy (m):  " + accuracyInMeters;
	}
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof LocationDatum)) {
			return false;
		}
		
		LocationDatum location = (LocationDatum)o;
		return latitude == location.latitude && longitude == location.longitude;		
	}
}