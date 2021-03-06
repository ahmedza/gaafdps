package com.gcaa.fplmb.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.StxEtxSerializer;
import com.gcaa.fplmb.entity.AftnMessageEntity;
import com.gcaa.fplmb.model.IcaoAftnMessage;
import com.gcaa.fplmb.parser.AmhsMessageParser;
import com.gcaa.fplmb.repository.AftnMsgRepository;

@Component
@Scope("prototype")
public class MessageProcessor {

	@Autowired
	private AftnMsgRepository aftnRepoistory;
	
	@Autowired
	private AmhsMessageParser parser;

	/*
	 * @Autowired private PublishingManager jmsManager;
	 */

	public void process(byte[] messageBbytes) {
		// Analyze Message Type
		// Parser it according to the type
		// Store IT in DB.
		// Create a DB queue for outgoing connections
		// Out to Q conditional
		// Out to Output sockets conditional
		
		IcaoAftnMessage msgModel = parser.parse(messageBbytes);	
		saveMessage(msgModel);
		sendToQueue(msgModel);
		/*
		 * jmsManager.sendMessage(null, new IcaoAftnMessage(), new
		 * String(messageBbytes));
		 */
	}
	
	private void sendToQueue(IcaoAftnMessage msgModel) {
		// TODO Auto-generated method stub
		
	}

	private void saveMessage(IcaoAftnMessage model){
		AftnMessageEntity entity = new AftnMessageEntity();
		entity.setRaw_Msg(new String(model.getRaw_Msg()));
		
		aftnRepoistory.save(entity);
	}

	public void deserializeMessage() {
		// getSerializer().deserialize(inputStream);
	}

	StxEtxSerializer getSerializer() {
		return new StxEtxSerializer();
	}
}