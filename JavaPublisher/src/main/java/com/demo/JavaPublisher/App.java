package com.demo.JavaPublisher;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       
    	ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("admin1", "admin2", "tcp://localhost:61616");
    	try 
    	{
    		javax.jms.Connection  connection = connectionfactory.createConnection();
    		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    		// session.createQueue() creates queue . if mention queue exists it wont create and if mentioned queue not exist it will create.
    		Destination destination = session.createQueue("demo");
    		//Message creation
    		
    		TextMessage textmessage = session.createTextMessage("13th Message");
    		//Producer creation
    		MessageProducer producer = session.createProducer(destination);
    		
    		
    		producer.send(textmessage);
    		
    		System.out.println("message is published");
    		
    		session.close();
    		connection.close();
    	}
    	catch(JMSException jmsexception)
    	{
    		
    	}
    }
}
