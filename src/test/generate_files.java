package test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class generate_files {
	private static String directory = "files/";
	static String[] offices = {"london", "berlin", "paris"};
	static String[] users = {"felix.pelaez", "juan.ruiz"};
	static String[] monthDays = {"05_23"};
	static String[] apps = {"document01"};
	static int num_docs = 1;
	
	public static String nowString() {
		int milli = 1;		
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
	}

	public static void generate_file(String office,String user,String monthDay,String app) {	
		for (int cont=0; cont < num_docs; cont ++) {
			FileWriter file;
			try {			
			    BufferedWriter output;
			    output = new BufferedWriter(new FileWriter(directory + String.format("%s_%s_%s%s_%d.log", office,user,monthDay,app, cont), true));
			    output.append(nowString() + " \"*********Starting scan********\";");
			    output.newLine();
			    output.append(nowString() + " \"Scan done. Image loaded in memory\";");
			    output.newLine();
			    output.append(nowString() + " \"Saving sample TIF image in share disc ... \";");
			    output.newLine();
			    output.append(nowString() + " \"Image TIF saved in shared disc\";");
			    output.newLine();
			    output.append(nowString() + " \"Loading image… \";");
			    output.newLine();
			    output.append(nowString() + " \"Image showed in applet\";");
			    output.close();			    
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(File file: new File(directory).listFiles()) 
			file.delete();
		
		int cont=0;
		for (String office : offices) {
			for (String user : users) {
				for (String monthDay : monthDays) {
					for (String app : apps) {
						generate_file(office,user,monthDay,app);
						cont++;
					}	
				}	
			}
		}
		System.out.println("num_docs:"+ cont);
	}

}
