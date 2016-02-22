package com.gcaa.tcp.socket.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.tcp.model.TcpConnectionModel;
import com.gcaa.tcp.socket.MGPSConnectionSocket;

@Component
@Scope("prototype")
public class MgpsTcpServer extends MGPSConnectionSocket {

	private static final Logger LOGGER = Logger.getLogger(MgpsTcpServer.class);

	private boolean terminate = false;
	private ServerSocket serverSocket = null;
	private List<Socket> connectedClients = new ArrayList<Socket>();
	private TcpConnectionModel tcpConnModel = null;

	public void init(TcpConnectionModel tcpServModel) throws Exception {
		tcpConnModel = tcpServModel;
		serverSocket = new ServerSocket(tcpServModel.getPort());
		LOGGER.info("Server Socket is Running...");
		LOGGER.info("Server is waiting for Connections");

		try {

			while (!terminate) {
				final Socket clientSocket = serverSocket.accept();

				new Thread(new Runnable() {

					@Override
					public void run() {
						communicateToSocket(clientSocket);
					}
				}).start();

				addConnectedClient(clientSocket);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/*
	 * public void communicateToSocket(Socket remoteClientSocket){
	 * 
	 * try(Scanner is = new Scanner(new
	 * BufferedInputStream(remoteClientSocket.getInputStream()));) { // Running
	 * continuously in-order to process Client Request while (true) { // Fetches
	 * the Client request byte[] byteData = receiveData(remoteClientSocket);
	 * String clientRequestMessage = new String(byteData).trim(); LOGGER.info(
	 * "Received Message = " + clientRequestMessage);
	 * 
	 * // Processing Client request String clientData =
	 * doProcess(clientRequestMessage);
	 * 
	 * // Sending Response to Client sendData(remoteClientSocket,
	 * clientData.getBytes()); LOGGER.info("Response Message sent to Client = "
	 * + clientData); } }catch (Exception e) { LOGGER.error(e.getMessage()); } }
	 */

	/**
     * Check for STX and ETX here And forward to DB and Output Channels
     *
     * @param message
     *            Client Request Message
     * @return Server Response Message
     */
    public String doProcess(String message)
    {
    	try{
	    	LOGGER.info("Received Message = " + message);
	    	if(message.equalsIgnoreCase("terminate")){
	    		terminate = false;
	    		if(serverSocket != null){
	    			serverSocket.close();
	    		}
	    	}
	    	
	    	if(message.length()>= "broadcast".length() && message.substring(0, "broadcast".length()).equalsIgnoreCase("broadcast")){
	    		/*connectedClients.*/
	    		String clientName = message.substring("broadcast".length()+1, message.indexOf("ExCxNAME"));
	    		sendToClient(clientName.trim(), "Response Message from Server - "+ tcpConnModel.getSocketName() + " " + message);
	    	}
	    	
	    	if (message.length()>= "getClients".length() && message.substring(0, "getClients".length()).equalsIgnoreCase("getClients")){
	    		return connectedClients.toString();
	    	}
    	
    		LOGGER.info("Processing the Client Request...");
            Date date = new Date(System.currentTimeMillis());
            return date.toString() + "\n";
    	}catch(Exception ex){
    		LOGGER.error("Error while processing the message " + message + ". Ignoring this message \n");
            
            return "Error while processing the message " + message + "\n. Ignoring this message and continuing processing the next. \n";
    	}
    }

	private void sendToClient(String clientName, String message) throws Exception {
		sendData(connectedClients.get(0), message.getBytes());
	}

	private void addConnectedClient(Socket e) {
		connectedClients.add(e);
	}

}