package exercise02;
public class ApprovalRepository implements Runnable {
	Integer requestID;
	public ApprovalRepository(Integer paramID) {
		super();
		this.requestID = paramID;
	}

	public ApprovalRequest getRequestId() {
		return new ApprovalRequest();
	}

	@Override
	public void run() {
		
	}

}
