
import java.lang.*;

public class InterfacePractice implements CharSequence {
	private String s;
	
	public InterfacePractice(String s) {
		this.s = s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public char charAt(int arg0) {
		if(this.s == null) {
			return '\0';
		}
		
		String[] allChars = this.s.split("");
		return 0;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CharSequence subSequence(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
