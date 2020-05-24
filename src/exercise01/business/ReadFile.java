package exercise01.business;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadFile implements Runnable{
	File file;
	String[] scan_range= {"**Starting scan**","Scan done."};
	int scan_average;
	int image_average;
	int show_average;
	
	public ReadFile(File file) {
		super();
		this.file=file;
	}

	private int getMillis(String line) {
		Integer result = 0;
		try {
			String[] parts=line.split(":");
			result = Integer.parseInt(parts[0]) * 3600000;
			result += Integer.parseInt(parts[1]) * 60000;
			parts=parts[2].split(".");
			result += Integer.parseInt(parts[0]) * 1000 + Integer.parseInt(parts[1]);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void run() {
		try {
			String line;
			try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
				while (sc.hasNextLine()){
					line = sc.nextLine();
					System.out.println(line);
					if (line.indexOf(scan_range[0]) > -1) {
						scan_average = getMillis(line);
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}

}
