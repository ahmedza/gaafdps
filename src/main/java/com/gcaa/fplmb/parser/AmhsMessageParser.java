package com.gcaa.fplmb.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.model.IcaoAftnMessage;

@Component
@Scope("prototype")
public class AmhsMessageParser extends AftnMessageParser{

	@Override
	public IcaoAftnMessage parse(byte[] messageBytes) {
		// TODO Auto-generated method stub

		IcaoAftnMessage model = new IcaoAftnMessage();
		model.setRaw_Msg(new String(messageBytes));
		
		return model;
	}

	
	
}
