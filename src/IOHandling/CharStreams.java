package IOHandling;

import java.io.*;

public class CharStreams {

	public static void main(String[] args) throws IOException{
		BufferedReader in = null;
		BufferedWriter out = null;
		
		try {
			in = new BufferedReader(new FileReader("ioSample.txt"));
			out = new BufferedWriter(new FileWriter("charOutput.txt"));
			
			int c;
			
			while((c = in.read()) != -1) {
				
				out.write(c);
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}

	}

}
