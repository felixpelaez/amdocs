package test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exercise04.Voice;

public class generate_exercise04 {
	private static String file_path = "c:\\tmp\\large_file.txt";
	static int registers = 5;
	
	public static void generate_file() {
		try {
			FileWriter file = new FileWriter (file_path);
			for (int cont=0; cont < registers; cont ++) {
					file.write((new Voice(new Long(cont).longValue(), "name" + cont, "surname" + cont, "address" + cont ,  "city" + cont , "cp" + cont, new Double(cont))).toJson() + "\n");
			}
			file.close();
			System.out.println("end processing");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		generate_file();
	}
}
