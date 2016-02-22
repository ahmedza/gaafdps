package com.gcaa.processor;

import com.gcaa.StxEtxSerializer;

public class MessageProcessor {

	public void process(byte[] messageBbytes){
		
/*		String message = deserializeMessage();
		persistMessage();
		invokeOutputEnds();*/
		
	}

	
	public void deserializeMessage(){
	//	getSerializer().deserialize(inputStream);
	}
	
	
	StxEtxSerializer getSerializer(){
		return new StxEtxSerializer();
	}
}
