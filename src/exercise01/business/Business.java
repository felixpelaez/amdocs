package exercise01.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import exercise01.bean.Data;
import exercise01.bean.Global;

public class Business {	
	private static String directory = "./files";
	private static Global global = new Global();
	private static List<ReadFile> threads = new Vector<ReadFile>();
	/**
	 * Insert file in the process. Its same how a filter.
	 * @param fileName: Name of the file, without path 
	 * @param officeName: Name of the office
	 * @param userName: Id of the user 
	 * @param monthDay: Month
	 * @param hour: Hour
	 * @return Boleean
	 */
	private static boolean insert(String fileName, String officeName,String userName,int monthDay,int hour) {		
		//<OFFICE_NAME>_<USER_NAME>_<MONTH_DAY>DocumentApp_<x>.log
		try {
			String[] parts = fileName.split("_");
			if ((parts[0].equals(officeName) || officeName.equals("")) &&
				(parts[1].equals(userName) || userName.equals("")) &&
				(Integer.parseInt(parts[2])==monthDay || monthDay<0) &&
				(parts[3].startsWith(Integer.toString(hour)) || hour<0)) {
				return true;				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		int array[] = {1,2,3,4};	
		String officeName="";
		String userName="";
		int monthDay=-1;
		int hour=-1;
		
		/**
		 * Set values of the files to search
		 */
		try {
			for (int i=0;  i < args.length; i++) {
				switch(args[i]) {
				  case "-day":
					monthDay = Integer.parseInt(args[++i]);
				    break;
				  case "-hour":
					hour = Integer.parseInt(args[++i]);
				    break;
				  case "-office":
					officeName = args[++i];
					break;
				  case "-username":
					userName = args[++i];
					break;
				  default:
					System.out.println("The parameter" + args[i] + " isn´t supported.");
				}
			}
		}catch (java.lang.NumberFormatException e) {
			System.out.println("The parameters 'day' and 'hour' must be numberic.");
			e.printStackTrace();			
		}catch (Exception e) {
			System.out.println("Error in parameters");
			e.printStackTrace();			
		}
		
		for(File file: new File(directory).listFiles()){
			if (insert(file.getName(),officeName,userName,monthDay,hour)) {
				ReadFile readFile = new ReadFile(file);
				new Thread(readFile).start();
				threads.add(readFile);
			}			
		}

		for(ReadFile readFile: threads) {
			global.addData(readFile);		
		}		
		
		global.printConsole();
	}
}
