package sys2202.phtis;

import java.time.LocalDateTime;

public class AccelerometerDatum extends Datum {

	private double x;
	private double y;
	private double z;
	
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
		
		super(deviceId, timestamp);
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Datum copy() {
		
		return new AccelerometerDatum(getDeviceId(), getTimestamp(), x, y, z);
	}
	
	public String toString() {
		
		return super.toString() + ", X:  " + x + ", Y:  " + y + ", Z:  " + z;
	}
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof AccelerometerDatum)) {
			return false;
		}
		
		AccelerometerDatum acceleration = (AccelerometerDatum)o;
		return x == acceleration.x && y == acceleration.y && z == acceleration.z;
	}
}