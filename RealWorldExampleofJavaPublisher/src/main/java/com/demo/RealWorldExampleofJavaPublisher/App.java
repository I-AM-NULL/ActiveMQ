package com.demo.RealWorldExampleofJavaPublisher;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

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
    		Destination destination = session.createQueue("realworldexample");
    		
    		JSONObject jsonobject = new JSONObject();
    		jsonobject.put("name","randy");
    		jsonobject.put("age", "10");
    		jsonobject.put("favouriteavenger", "Thor");
    		
    		
    		//Message creation
    		TextMessage textmessage = session.createTextMessage(jsonobject.toString());
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
