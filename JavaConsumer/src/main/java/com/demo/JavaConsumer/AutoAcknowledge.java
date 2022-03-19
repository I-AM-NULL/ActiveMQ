package com.demo.JavaConsumer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AutoAcknowledge {
	
	
	public static void main( String[] args )
    {
        // Broker connection is established
    	ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("admin1", "admin2", "tcp://localhost:61616");
    	try 
    	{
    		javax.jms.Connection  connection = connectionfactory.createConnection();
    		connection.start();
    		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    		// session.createQueue() creates queue . if mention queue exists it wont create and if mentioned queue not exist it will create.
    		Destination destination = session.createQueue("demo");
    		
    		// consumer is created
    	    MessageConsumer consumer = session.createConsumer(destination);
    	    
    	   consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage texmessage = (TextMessage) message;
				try
				{
					System.out.println(texmessage.getText());
				}
				catch(JMSException jmsexception)
				{
					System.out.println(jmsexception.getMessage());
				}
				
			}
		});
    	}
    	catch(JMSException jmsexception)
    	{
    		
    	}
    }

}
