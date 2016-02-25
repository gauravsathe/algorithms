package IOHandling;

import java.io.*;

public class IOPractice {

	public static void main(String[] args) {
		// Create new file
		/*
		try {
			File newFile = new File("newFile.txt");
			
			boolean canCrt = newFile.createNewFile();
			
			if(!canCrt) {
				System.out.println("File already exists");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// Read file using FileInputStream and BufferedStreamReader
		File f = new File("charOutput.txt");
		FileInputStream fis = null;
		BufferedInputStream buff = null;
		
		try {
			fis = new FileInputStream(f);
			buff = new BufferedInputStream(fis);
			
			int c;
			while((c = buff.read()) != -1) {
				System.out.format("%c", (char)c);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			
				if(buff != null) {
					buff.close();
				}
			} catch(IOException e) {}
		}
		*/
		
		File f = new File("charOutput.txt");
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
