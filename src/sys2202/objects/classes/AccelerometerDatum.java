package sys2202.objects.classes;

import java.time.LocalDateTime;

public class AccelerometerDatum {

	private String deviceId;
	private LocalDateTime timestamp;
	private double x;
	private double y;
	private double z;
	
	public String getDeviceId() {

		return deviceId;
	}

	public LocalDateTime getTimestamp() {

		return timestamp;
	}
	
	public double getX() {
		
		return x;
	}
	
	public double getY() {
		
		return y;
	}
	
	public double getZ() {
		
		return z;
	}
	
	public AccelerometerDatum(String deviceId, LocalDateTime timestamp, double x, double y, double z) {
		
		this.deviceId = deviceId;
		this.timestamp = timestamp;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		
		return "Device:  " + deviceId + ", Timestamp:  " + timestamp + ", X:  " + x + ", Y:  " + y + ", Z:  " + z;
	}
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof AccelerometerDatum)) {
			return false;
		}
		
		AccelerometerDatum acceleration = (AccelerometerDatum)o;
		return x == acceleration.x && y == acceleration.y && z == acceleration.z;
	}
}