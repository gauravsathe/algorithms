
class Point{
	public int x;
	public int y;
	public Point() {
		x = 0;
		y = 0;
	}
	public Point(int x1, int y1) {
		x = x1;
		y = y1;
	}
}

public class Misc {
	
	public static int myAtoi(String str) {
		int integer = 0;
		int sign = 1;
		int i=0;
		
		if(str.length() == 0) return 0;
		
		while(str.charAt(i) == ' ') {
			i++;
		}
		
		if(str.charAt(i) == '+') {
			sign = 1;
			i++;
		}
		else if(str.charAt(i) == '-') {
			sign = -1;
			i++;
		}
		
		for(; i<str.length(); i++) {
			if((int)str.charAt(i) >= 48 && (int)str.charAt(i) <= 57 ) {
				if( ((((long)integer)*10) + ((int)str.charAt(i)-48))*sign > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				else if( ((((long)integer)*10) + ((int)str.charAt(i)-48))*sign < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
				integer = (integer*10) + ((int)str.charAt(i)-48);
			}
			else {
				break;
			}
		}
		
		return integer*sign;
	}
	
	public static int MaxPointsOnLine(PointObj[] points) {
		if(points.length == 0) return 0;
        
        int[] optimal_solution = new int[points.length];
        double[] slope = new double[points.length];
        
        int i=0,j=0;
        double current_slope;
        for(i=0; i<points.length; i++) {
            optimal_solution[i] = 1;
            slope[i] = 0.0;
        }
        
        for(i=0; i<points.length; i++) {
            for(j=0;j<i;j++) {
                if(points[i].getX() == points[j].getX()) {
                    if(points[i].getY() == points[j].getY()) {
                        current_slope = 0;
                    }
                    else {
                        current_slope = Integer.MAX_VALUE;
                    }    
                }
                else {
                    current_slope = ((points[i].getY()-points[j].getY())*1.0)/(points[i].getX()-points[j].getX());
                }
                
                if(slope[j] == 0 || current_slope == 0) {
                    optimal_solution[i] = optimal_solution[j] + 1;
                    slope[i] = current_slope;
                }
                else {
                    if( (current_slope == slope[j]) && (optimal_solution[j]+1 > optimal_solution[i]) ) {
                        optimal_solution[i] = optimal_solution[j] + 1;
                        slope[i] = current_slope;
                    }
                }
            }
            for(j=0;j<points.length;j++) {
            	System.out.print(slope[j] + "\t");
            }
            System.out.println();
        }
        
        int max_points = optimal_solution[0];
        for(i=1; i<optimal_solution.length; i++) {
            if(optimal_solution[i] > max_points) {
                max_points = optimal_solution[i];
            }
        }
        
        return max_points;	
	}
	
	public static void main(String[] args) {
		PointObj[] points = new PointObj[5];
		
		points[0] = new PointObj(-4,4);
		points[1] = new PointObj(-8,-582);
		points[2] = new PointObj(-3,3);
		points[3] = new PointObj(-9,-651);
		points[4] = new PointObj(9,591);
		
		System.out.println(MaxPointsOnLine(points));
		
	}

}
