package exercise04;
public class UserRepository implements Runnable {
	User user;
	Integer userID;
	public UserRepository(Integer paramID) {
		super();
		this.userID = paramID;
	}

	public User getUser() {		
		return new User();
	}

	@Override
	public void run() {
		
	}

}
