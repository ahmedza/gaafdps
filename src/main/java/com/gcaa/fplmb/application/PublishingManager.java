package com.gcaa.fplmb.application;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.tcp.model.icao.IcaoAftnMessage;

//@EnableJms
@Component
public class PublishingManager {

	@Autowired
	private JmsTemplate outTemplate;

	public void sendMessage(Destination destinationQueue, IcaoAftnMessage messageObj, String message) {

		outTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session jmsSession) throws JMSException {
				// TODO Auto-generated method stub
				ObjectMessage objMessage = jmsSession.createObjectMessage();
				objMessage.setStringProperty("String Form", message);
				objMessage.setObject(messageObj);
				return objMessage;
			}
		});
	}
}