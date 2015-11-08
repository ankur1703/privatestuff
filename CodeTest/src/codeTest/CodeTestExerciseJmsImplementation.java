package codeTest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CodeTestExerciseJmsImplementation extends CodeTestExerciseJms {
	
	private static QueueSession session;
	private static Context context;
	
	public static void main(String[] args) throws NamingException, JMSException {
		CodeTestExerciseJmsImplementation implementation = new CodeTestExerciseJmsImplementation();
		
		context = new InitialContext(); //initial context
		QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup(QUEUE_CONNECTION_FACTORY); // Connecting to the connection factory
		QueueConnection connection = factory.createQueueConnection(); // creating queue connection
		session = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE); // creating a session
		connection.start();
			
		implementation.process();
				
		connection.stop();
		session.close();
		
	}

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		try {
			System.out.println("Message Received: " + new StringBuilder(textMessage.getText()).reverse());
			if (session.getAcknowledgeMode() == Session.CLIENT_ACKNOWLEDGE){
				message.acknowledge(); // acknowledge message
			}			
		}
		catch (JMSException e) {
			System.out.println("JMS Exception occured on 'onMessage' method. Message: " + e.getMessage());
			e.printStackTrace();
		}		
	}

	@Override
	public void sendMessage() {
		try {
			Queue sendQueue = (Queue)context.lookup(SEND_QUEUE); // connecting to send queue 
			MessageProducer producer = session.createProducer(sendQueue); // creating a producer on the send queue
			TextMessage message = session.createTextMessage(); // creating a text message
			message.setText("Hellow, this is a test MESSAGE!!!"); // setting a message on TextMessage object
			producer.send(message);	// sending the message
			producer.close(); // closing the producer
		} catch (NamingException e) {
			System.out.println("NamingException occured on 'sendMessage' method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		catch (JMSException e) {
			System.out.println("JMS Exception occured on 'sendMessage' method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public void registerMessageListener() {
		try {
			Queue replyQueue = (Queue)context.lookup(REPLY_QUEUE); // connecting to reply queue
			MessageConsumer consumer = session.createConsumer(replyQueue); // creating a consumer
			consumer.setMessageListener(this); // registering the consumer to the message listener
			
		} catch (NamingException e) {
			System.out.println("NamingException occured on 'registerMessageListener' method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		catch (JMSException e) {
			System.out.println("JMS Exception occured on 'registerMessageListener' method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
