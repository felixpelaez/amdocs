package exercise04;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Voice {
 long id;
 String name;
 String surname;
 String address;
 String city;
 String cp;
 Double total;
 
public Voice(long id, String name, String surname, String address, String city, String cp, Double total) {
	super();
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.address = address;
	this.city = city;
	this.cp = cp;
	this.total = total;
}

public Voice(String paramVoice) {
	super();
	String[] values = paramVoice.split(",");
	this.id = Integer.parseInt((values[0].split(":")[1]));
	this.name = values[1].split(":")[1];
	this.address =  values[2].split(":")[1];
	this.city =  values[3].split(":")[1];
	this.cp =  values[4].split(":")[1];
	this.total = new Double((values[5].split(":")[1]));
	this.surname =  values[6].split(":")[1];
	
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getCp() {
	return cp;
}

public void setCp(String cp) {
	this.cp = cp;
}

public Double getTotal() {
	return total;
}

public void setTotal(Double total) {
	this.total = total;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + ((cp == null) ? 0 : cp.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((total == null) ? 0 : total.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Voice other = (Voice) obj;
	if (surname == null) {
		if (other.surname != null)
			return false;
	} else if (!surname.equals(other.surname))
		return false;
	if (address == null) {
		if (other.address != null)
			return false;
	} else if (!address.equals(other.address))
		return false;
	if (city == null) {
		if (other.city != null)
			return false;
	} else if (!city.equals(other.city))
		return false;
	if (cp == null) {
		if (other.cp != null)
			return false;
	} else if (!cp.equals(other.cp))
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (total == null) {
		if (other.total != null)
			return false;
	} else if (!total.equals(other.total))
		return false;
	return true;
}

public String toJson() {
	 ObjectMapper mapper = new ObjectMapper();
	 try {
	   return mapper.writeValueAsString(this);
	 } catch (JsonProcessingException e) {
	    e.printStackTrace();
	 }
	 return "";
 } 
}
