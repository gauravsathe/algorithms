
public class Karatsuba {

	public static void main(String[] args) {
		Karatsuba k1 = new Karatsuba();
				
				Number_Int num1 = new Number_Int();
				Number_Int num2 = new Number_Int();
				
				num1.setNumber(1234);
				num2.setNumber(5678);
				
				System.out.println("Karatsuba Product = " + k1.Multiply(num1, num2));

	}
	
	public int Multiply(Number_Int num1, Number_Int num2) {
			
			int n = num1.getLength();
			
			if(n == 1) {
				return num1.getValue() * num2.getValue();
			}
			
			int x = num1.getValue();
			int y = num2.getValue();
			
			Number_Int a = new Number_Int();
			Number_Int b = new Number_Int();
			Number_Int c = new Number_Int();
			Number_Int d = new Number_Int();
			Number_Int a_plus_b = new Number_Int();
			Number_Int c_plus_d = new Number_Int();
			
			a.setNumber((int) (x / Math.pow(10, (int)n/2)));
			b.setNumber((int) (x % Math.pow(10, (int)n/2)));
			c.setNumber((int) (y / Math.pow(10, (int)n/2)));
			d.setNumber((int) (y % Math.pow(10, (int)n/2)));
			a_plus_b.setNumber(a.getValue()+b.getValue());
			c_plus_d.setNumber(c.getValue()+d.getValue());
			
			int ac = Multiply(a, c);
			int bd = Multiply(b, d);
			int ab_cd = Multiply(a_plus_b, c_plus_d);
			
			return (int)(ac*Math.pow(10, n) + bd + (ab_cd - ac - bd)*Math.pow(10, (int)n/2));
	}
}
