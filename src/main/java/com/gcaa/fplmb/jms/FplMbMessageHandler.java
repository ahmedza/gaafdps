package com.gcaa.fplmb.jms;

import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import org.springframework.messaging.MessagingException;

import com.gcaa.fplmb.tcp.model.icao.IcaoAftnMessage;

public class FplMbMessageHandler/* implements MessageHandler*/ {

	private static final Logger logger = Logger.getLogger(FplMbMessageHandler.class);
	
	/*@Override*/
	public void handleMessage(IcaoAftnMessage arg0) throws MessagingException {
		// Receive incoming messages and store in DB. Use Application manager to parse and persist messages in db.
		logger.info("received message in IcaoAftn" + arg0.toString());
	}

	
	public void handleMessage(String arg0) throws MessagingException {
		// Receive incoming messages and store in DB. Use Application manager to parse and persist messages in db.
		logger.info("received message in String" + arg0.toString());		
	}
	
	public void handleMessage(ObjectMessage arg0) throws MessagingException {
		// Receive incoming messages and store in DB. Use Application manager to parse and persist messages in db.
		logger.info("received message in ObjectMessage" + arg0.toString());		
	}
}
