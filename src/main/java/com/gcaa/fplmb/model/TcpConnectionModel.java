package com.gcaa.fplmb.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;


public class TcpConnectionModel {

	private String				host;			// host to connect
	private int					port;			// port of the serverSocket to connect
	private String 				socketType;   	// client or server
	private String				socketName;		// Logical name to identify the socket
	private InputStream			dis;			// Datainput Stream for the sockets
	private DataOutputStream	dos;			// DataOutPutStream for the sockets
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getSocketType() {
		return socketType;
	}
	public void setSocketType(String socketType) {
		this.socketType = socketType;
	}
	public InputStream getDis() {
		return dis;
	}
	public void setDis(InputStream dis) {
		this.dis = dis;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	public String getSocketName() {
		return socketName;
	}
	public void setSocketName(String socketName) {
		this.socketName = socketName;
	}	
	
}