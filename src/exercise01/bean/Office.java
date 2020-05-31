package exercise01.bean;

import java.util.HashMap;
import java.util.Map;

public class Office {
	private String name="";
	private int docsNum=0;
	HashMap<String, User> users = new HashMap<String, User>();
	
	public Office() {
		super();
	}
	
	public Office(String officeName,String userName, Integer day, Integer hour, Data data) {
		super();
		this.name=officeName;
		this.addData(userName,day,hour,data);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String officeName) {
		this.name = officeName;
	}

	public int getDocsNum() {
		return docsNum;
	}

	public void setDocsNum(int docsNum) {
		this.docsNum = docsNum;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public void addData(String key,Integer day, Integer hour, Data data) {
		this.docsNum += data.getDocNum();
		User store = this.users.get(key);
		if (store==null) {
			this.users.put(key,new User(key,day,hour,data));
		}else {			
			store.addData(day,hour,data);
			this.users.put(key,store);		
		}
	}
	
	public void printConsole() {
		for (Map.Entry<String, User> entry : this.users.entrySet()) {
			System.out.println(String.format("User %s:", entry.getKey()));
			System.out.println(String.format("%d documents analysed", entry.getValue().getDocsNum()));
			entry.getValue().printConsole();
		}
	}
}
