package test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class generate_files {
	private static String directory = "files/exercise01/";
	static String[] offices = {"madrid", "barcelona", "london", "berlin", "paris"};
	static String[] users = {"felix.pelaez", "juan.ruiz", "jose.martin", "john.smith"};
	static String[] monthDays = {"05_22", "05_23", "05_24"};
	static String[] apps = {"document01", "document02", "document03"};
	static int num_docs = 5;
	
	public static String nowString() {
		int random = (int)(1 * Math.random() + 3);		
		try {
			System.out.println("==>" + random);
			Thread.sleep(random * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
	}

	public static void generate_file(String office,String user,String monthDay,String app) {	
		for (int cont=0; cont < num_docs; cont ++) {
			FileWriter file;
			try {
				file = new FileWriter (directory + String.format("%s_%s_%s%s_%d.log", office,user,monthDay,app, cont));
				file.write(nowString() + " \"*********Starting scan********\";");
				file.write(nowString() + " \"Scan done. Image loaded in memory\";");
				file.write(nowString() + " \"Saving sample TIF image in share disc ... \";");
				file.write(nowString() + " \"Image TIF saved in shared disc\";");
				file.write(nowString() + " \"Loading image… \";");
				file.write(nowString() + " \"Image showed in applet\";");
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(File file: new File(directory).listFiles()) 
			file.delete();
					
		for (String office : offices) {
			for (String user : users) {
				for (String monthDay : monthDays) {
					for (String app : apps) {
						generate_file(office,user,monthDay,app);
					}	
				}	
			}
		}
	}

}
