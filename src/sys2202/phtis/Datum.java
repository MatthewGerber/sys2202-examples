package sys2202.phtis;

import java.time.LocalDateTime;

public abstract class Datum {

	private String deviceId;
	private LocalDateTime timestamp;

	public String getDeviceId() {

		return deviceId;
	}

	public LocalDateTime getTimestamp() {

		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		
		this.timestamp = timestamp;
	}

	public Datum(String deviceId, LocalDateTime timestamp) {

		this.deviceId = deviceId;
		this.timestamp = timestamp;
	}

	public abstract Datum copy();
	
	public String toString() {
		
		return "Device:  " + deviceId + ", Time:  " + timestamp;
	}
}