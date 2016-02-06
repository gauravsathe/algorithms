
public class Variables {

	public static void main(String[] args) {
		
		Bicycle b = new Bicycle(20, 40, 4);
		Bicycle mb = new MountainBike(33, 20, 3, 10);
		Bicycle rb = new RoadBike(30, 2, 50, "Dual");
		
		b.description();
		mb.description();
		rb.description();
		
		try {
			Bicycle bClone = b.clone();
			bClone.description();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		
	}

}