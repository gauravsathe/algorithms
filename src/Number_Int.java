
public class Number_Int {
	private int m_value;
	private int m_length;
	
	public int getValue() {	return m_value;	}
	
	public int getLength() { return m_length; }
	
	public void setNumber(int value) {
		m_value = value;
		m_length = 1;
		
		while(value/10 != 0) {
			m_length += 1;
			value /= 10;
		}
	}
}
