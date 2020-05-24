package exercise02;

import java.util.HashMap;

public class RepositoryCache {
	HashMap<Integer, User> cacheUsers = new HashMap<Integer, User>();
	HashMap<Integer, ApprovalRequest> cacheRequests = new HashMap<Integer, ApprovalRequest>();	
		
	public User getUser(Integer paramId) {
		return cacheUsers.get(paramId);
	}
	
	public ApprovalRequest getRequest(Integer paramId) {
		return cacheRequests.get(paramId);
	}
}