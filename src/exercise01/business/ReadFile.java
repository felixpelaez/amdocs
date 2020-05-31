package exercise01.business;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import exercise01.bean.Data;
import exercise01.util.CTES;

public class ReadFile implements Runnable{
	String office;
	String user;
	int day;
	int hour;
	File file;
	private int statusData; // 0: It´s reading the file, 1:It´s finished the reading, 2: It´s ended.
	private Data data = new Data();
	
	public ReadFile(File file) {
		super();
		this.file=file;
		String[] parts = file.getName().split("_");
		this.office=parts[0];
		this.user=parts[1];
		this.day= new Integer(parts[2]);
		this.hour = new Integer(parts[3].substring(0,2));
		System.out.println("===>");
	}
	
	
	public int getstatusData() {
		return statusData;
	}

	public void setstatusData(int statusData) {
		this.statusData = statusData;
	}

	public Data getData() {
		try {
			int cont=0;
			while (cont++ < 10 && (this.statusData < 2)) {
				if (this.statusData==1) {
					this.setstatusData(2);
				}else {
					Thread.sleep(CTES.SLEEP);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data;
	}

	public File getFile() {
		return file;
	}
	
	public String getOffice() {
		return office;
	}


	public void setOffice(String office) {
		this.office = office;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setData(Data data) {
		this.data = data;
	}

	private int getMillis(String line) {
		Integer result = 0;
		try {
			String[] parts=line.split(" \"")[0].split(":");
			result = Integer.parseInt(parts[0]) * 3600000;
			result += Integer.parseInt(parts[1]) * 60000;
			parts=parts[2].split("\\.");
			result += Integer.parseInt(parts[0]) * 1000 + Integer.parseInt(parts[1]);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void processLogLine(String officeName,String userName,int monthDay,int hour, String strLine) {
		try {
			System.out.println(strLine);
			if (strLine.indexOf(Data.getScanRange()[0]) > -1) {
				data.setScan_average(getMillis(strLine));
			} else 	if (strLine.indexOf(Data.getScanRange()[1]) > -1) {
				data.setScan_average(getMillis(strLine) - data.getScan_average());
 			} else 	if (strLine.indexOf(Data.getImageRange()[0]) > -1) {
				data.setImage_average(getMillis(strLine));
 			} else 	if (strLine.indexOf(Data.getImageRange()[1]) > -1) {
				data.setImage_average(getMillis(strLine) - data.getImage_average());
				data.setShow_average(getMillis(strLine));
  			} else 	if (strLine.indexOf(Data.getShowRange()[1]) > -1) {
				data.setShow_average(getMillis(strLine) - data.getShow_average());
				data.setDocNum(data.getDocNum() + 1);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			try (Scanner sc = new Scanner(this.file)) {
				while (sc.hasNextLine()){
					processLogLine(this.office,this.user,this.day,this.hour,sc.nextLine());
				}
				this.setstatusData(1);
				while (this.getstatusData()< 2){
					Thread.sleep(CTES.SLEEP);
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
}
