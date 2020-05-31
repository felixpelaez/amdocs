package exercise01.bean;

import java.util.HashMap;
import java.util.Map;

public class Hour {
	Integer hour = new Integer(0);
	private int docsNum=0;
	HashMap<Integer, Data> hours = new HashMap<Integer, Data>();
	
	public Hour(Integer hour, Data data) {
		super();
		this.hour=hour;
		this.hours.put(hour,data);
	}
	
	public Data sum(Data first, Data second) {
		Data result = new Data();
		result.setDocNum(first.getDocNum() + second.getDocNum());
		result.setImage_average((first.getImage_average() + second.getImage_average())/this.docsNum);
		result.setScan_average((first.getScan_average() + second.getScan_average())/this.docsNum);
		result.setShow_average((first.getShow_average() + second.getShow_average())/this.docsNum);
		return result;
	}
	
	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public int getDocsNum() {
		return docsNum;
	}

	public void setDocsNum(int docsNum) {
		this.docsNum = docsNum;
	}

	public HashMap<Integer, Data> getHours() {
		return hours;
	}

	public void setHours(HashMap<Integer, Data> hours) {
		this.hours = hours;
	}

	public void addData(Integer hour, Data data) {
		this.docsNum += data.getDocNum();
		Data store = this.hours.get(hour);
		if (store==null) {
			this.hours.put(hour,data);
		}else {
			this.hours.put(hour,sum(store,data));		
		}
	}
	
	public void printConsole() {
		for (Map.Entry<Integer, Data> entry : this.hours.entrySet()) {
			entry.getValue().printConsole();
		}
	}

	
}
