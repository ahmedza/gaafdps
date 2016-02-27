package com.gcaa.fplmb.parser;

import com.gcaa.fplmb.model.IcaoAftnMessage;

public abstract class AftnMessageParser {

	public abstract IcaoAftnMessage parse(byte[] messageBytes);	
	
}
