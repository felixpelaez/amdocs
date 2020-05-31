package exercise01.bean;

import java.util.HashMap;
import java.util.Map;

public class User {
	private String name="";
	private int docsNum=0;
	HashMap<Integer, Day> users = new HashMap<Integer, Day>();
	
	public User(String userName, Integer day, Integer hour, Data data) {
		super();
		this.name=userName;
		this.addData(day,hour,data);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDocsNum() {
		return docsNum;
	}

	public void setDocsNum(int docsNum) {
		this.docsNum = docsNum;
	}

	public HashMap<Integer, Day> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, Day> users) {
		this.users = users;
	}

	public void addData(Integer day, Integer hour, Data data) {
		this.docsNum += data.getDocNum();
		Day store = this.users.get(day);
		if (store==null) {
			this.users.put(day,new Day(day,hour,data));
		}else {			
			store.addData(day,hour,data);
			this.users.put(day,store);		
		}
	}
	
	public void printConsole() {
		for (Map.Entry<Integer, Day> entry : this.users.entrySet()) {
			System.out.println(String.format("Day %s:", entry.getKey()));
			System.out.println(String.format("%d documents analysed", entry.getValue().getDocsNum()));
			entry.getValue().printConsole();
		}
	}
}
