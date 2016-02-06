
public class Bicycle implements Cloneable{
	private int cadence;
	private int speed;
	private int gear;
	private int Id;
	
	public static int nextId = 0;
	
	private static void setId() {
		if(nextId == 0) {
			nextId = 1;
		}
	}
	
	public Bicycle(int cad, int speed, int gear) {
		this.cadence = cad;
		this.speed = speed;
		this.gear = gear;
		
		this.setId();
		this.Id = nextId++;
	}
	
	public int getId() {
		return this.Id;
	}
	
	public int getCadence() {
		return this.cadence;
	}
	
	public void setCadence(int newCad) {
		this.cadence = newCad;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void applyBrake(int decSpeed) {
		if(this.speed < decSpeed) this.speed = 0;
		else this.speed -= decSpeed;
	}
	
	public void speedUp(int incSpeed) {
		this.speed += incSpeed;
	}
	
	public int getGear() {
		return this.gear;
	}
	
	public void setGear(int newGear) {
		this.gear = newGear;
	}
	
	//@Override
	public void description() {
		System.out.println("Bike with Id = " + this.getId() + " in gear = " + this.getGear() + " has cadence = " + this.getCadence() + " and speed = " + this.getSpeed());
	}
	
	@Override
	public Bicycle clone() throws CloneNotSupportedException {
		Bicycle c = (Bicycle)super.clone();
		c.Id = Bicycle.nextId++;
		
		return c;
		//return new Bicycle(this.getCadence(), this.getSpeed(), this.getGear());
	}
	
}
