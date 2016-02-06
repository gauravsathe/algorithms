
public class MountainBike extends Bicycle {
	public int seatHeight;
	
	public MountainBike(int startCadence, int startSpeed, int startGear, int seatHeight) {
		super(startCadence, startSpeed, startGear);
		this.seatHeight = seatHeight;
	}
	
	public void setHeight(int newHeight) {
		this.seatHeight = newHeight;
	}
	
	@Override
	public void description() {
		super.description();
		System.out.println("MountainBike has seatHeight = " + this.seatHeight);
	}
 }

class RoadBike extends Bicycle {
	private String suspension;
	
	public RoadBike(int cad, int gear, int speed, String susp) {
		super(cad, speed, gear);
		this.setSuspension(susp);
	}

	public String getSuspension() {
		return suspension;
	}

	public void setSuspension(String suspension) {
		this.suspension = suspension;
	}
	
	@Override
	public void description() {
		super.description();
		System.out.println("RoadBike has suspension = " + this.getSuspension());
	}
}