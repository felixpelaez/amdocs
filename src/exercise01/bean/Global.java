package exercise01.bean;

import java.util.HashMap;
import java.util.Map;

import exercise01.business.ReadFile;

public class Global {
	private String name="";
	private int docsNum=0;
	HashMap<String, Office> offices = new HashMap<String, Office>();
	
	public Global() {		
		super();
	}
	
	public void addData(ReadFile readFile) {
		Data data = readFile.getData();
		this.docsNum += data.getDocNum();
		Office store = this.offices.get(readFile.getOffice());
		if (store==null) {
			this.offices.put(readFile.getOffice(),new Office(readFile.getOffice(),readFile.getUser(),readFile.getDay(),readFile.getHour(),data));
		}else {			
			store.addData(readFile.getUser(),readFile.getDay(),readFile.getHour(),data);
			this.offices.put(this.name,store);		
		}
	}
	
	public void addData_borrar(String office, String user, Integer day, Integer hour, Data data) {
		this.docsNum += data.getDocNum();
		Office store = this.offices.get(office);
		if (store==null) {
			this.offices.put(office,new Office(office,user,day,hour,data));
		}else {			
			store.addData(user,day,hour,data);
			this.offices.put(this.name,store);		
		}
	}
	
	public void printConsole() {
		for (Map.Entry<String, Office> entry : this.offices.entrySet()) {
			System.out.println(String.format("Office %s:", entry.getKey()));
			System.out.println(String.format("%d documents analysed", entry.getValue().getDocsNum()));
			entry.getValue().printConsole();
		}
	}
	


}
