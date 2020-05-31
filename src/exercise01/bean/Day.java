package exercise01.bean;

import java.util.HashMap;
import java.util.Map;

public class Day {
	private Integer day= new Integer(0);
	private int docsNum=0;
	
	HashMap<Integer, Hour> days = new HashMap<Integer, Hour>();
	
	public Day(Integer day, Integer hour, Data data) {
		super();
		this.day =day;
		this.addData(day,hour,data);
	}
	
	public void addData(Integer day, Integer hour, Data data) {
		this.docsNum += data.getDocNum();
		Hour store = this.days.get(day);
		if (store==null) {
			this.days.put(day,new Hour(hour,data));
		}else {
			store.addData(hour,data);
			this.days.put(day,store);		
		}
	}
	
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public int getDocsNum() {
		return docsNum;
	}

	public void setDocsNum(int docsNum) {
		this.docsNum = docsNum;
	}

	public HashMap<Integer, Hour> getDays() {
		return days;
	}

	public void setDays(HashMap<Integer, Hour> days) {
		this.days = days;
	}

	public void printConsole() {
		for (Map.Entry<Integer, Hour> entry : this.days.entrySet()) {
			System.out.println(String.format("Hour: %d", this.day));
			System.out.println(String.format("%d documents analysed", this.docsNum));
			entry.getValue().printConsole();
		}
	}
	
}
