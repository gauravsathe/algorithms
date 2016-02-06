
public class Rectangle implements Relatable{
	private PointObj origin;
	private int length;
	private int width;
	
	public Rectangle() {
		this(0,0);
	}
	
	public Rectangle(PointObj o) {
		this(o,0,0);
	}
	
	public Rectangle(int len, int wid) {
		this(new PointObj(0, 0), len, wid);
	}
	
	public Rectangle(PointObj o, int len, int wid) {
		this.origin = o;
		this.length = len;
		this.width = wid;
	}
	
	public void moveRect(int x, int y) {
		this.origin.setX(x);
		this.origin.setY(y);
	}
	
	public int areaRect() {
		return this.length * this.width;
	}
	
	public int isLargerThan(Relatable other) {
		Rectangle otherRect = (Rectangle)other;
		
		if(this.areaRect() == otherRect.areaRect()) {
			return 0;
		}
		else if(this.areaRect() < otherRect.areaRect()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
