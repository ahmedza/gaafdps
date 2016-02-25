package com.gcaa.fplmb.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.StxEtxSerializer;

@Component
@Scope("prototype")
public class MessageProcessor {

/*	@Autowired
	private AFTNMsgRepository aftnRepoistory;*/

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

		/*
		 * jmsManager.sendMessage(null, new IcaoAftnMessage(), new
		 * String(messageBbytes));
		 */
	}

	public void deserializeMessage() {
		// getSerializer().deserialize(inputStream);
	}

	StxEtxSerializer getSerializer() {
		return new StxEtxSerializer();
	}
}
