package exercise02;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMQ {		
	static RepositoryCache cacheRep = new RepositoryCache();
	public static void main(String[] args) {	
		boolean autoAck = false;
		Channel channel = sender.get().createChannel();
		channel.basicConsume(queueName, autoAck, "a-consumer-tag",
		new DefaultConsumer(channel) {
			 public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
				long deliveryTag = envelope.getDeliveryTag();
				String message = new String(body, "UTF-8");
				try{
					Mapper objectMapper;
					ApprovalRequestMessage approvalRequestMessage = objectMapper.readValue(message, ApprovalRequestMessage.class);
					User user=cacheRep.getUser(approvalRequestMessage.getUserId());
		
					if (user==null){
						UserRepository userRepository = new UserRepository(approvalRequestMessage.getUserId());						
						new Thread(userRepository).start();
						user=userRepository.getUser();
					}
										
					ApprovalRequest approvalRequest = cacheRep.getRequest(approvalRequestMessage.getUserId());					
					if (approvalRequest==null){
						ApprovalRepository approvalRepository = new ApprovalRepository(approvalRequestMessage.getRequestId());						
						new Thread(approvalRepository).start();
						approvalRequest=approvalRepository.getRequestId();
					}
					
					//invoking rule engine to validate request
					Evaluation evaluation=evaluateApprovalRequest(user.getCIF(),approvalRequest.getBoundaries());
					if (evaluation.status().equals("GREEN")){
						LOGGER.log("Request approved. Request ID:",approvalRequestMessage.getRequestId());
						channel.basicAck(deliveryTag, true);
					}else{
						LOGGER.log("Request needs second evaluation. Request ID:",approvalRequestMessage.getRequestId());
					}
				}catch(Exception e){
					LOGGER.log("Technical issue");
				}
			 }

			private Evaluation evaluateApprovalRequest(String cif, String boundaries) {
				// TODO Auto-generated method stub
				return null;
			}
		}
	}
}
