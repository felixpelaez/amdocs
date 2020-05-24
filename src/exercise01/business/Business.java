package exercise01.business;

import java.io.File;

public class Business {
	private static String directory = "files/exercise01/";
	
	public static void main(String[] args) {
		for(File file: new File(directory).listFiles()){
			ReadFile readFile = new ReadFile(file);
			new Thread(readFile).start();			
		}
	}
}
