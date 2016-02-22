package com.gcaa.tcp.socket.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.tcp.model.TcpConnectionModel;
import com.gcaa.tcp.socket.MGPSConnectionSocket;


@Component
@Scope("prototype")
public class MgpsTcpClient extends MGPSConnectionSocket {

	Logger LOGGER = Logger.getLogger(MgpsTcpClient.class);

	private TcpConnectionModel tcpClient;

	Socket socket = null;

	public void init(TcpConnectionModel tcpClient) throws Exception {
		this.tcpClient = tcpClient;
		socket = new Socket(tcpClient.getHost(), tcpClient.getPort());
		// Create Client Request
		String request = "CLIENT_REQUEST:SEND_SYSTEM_TIME \n Client socket name = " + tcpClient.getSocketName();
		sendData(socket, request.getBytes());
		
/*		receiveData(socket);*/
//		updateTcpModel(tcpClient);
		communicateToSocket(socket);
	}

/*	@Override
	public void communicateToSocket(Socket remoteClientSocket) {
		try ( BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
				DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));) {
			
			

			// Create Client Request
			String request = "CLIENT_REQUEST:SEND_SYSTEM_TIME \n Client socket name = " + tcpClient.getSocketName();

			// Send Client Request to Server
			sendData(remoteClientSocket, request.getBytes());

			LOGGER.info("Initial Byte Sent to Server");
			Scanner scan = new Scanner(is);
			while (true) {
				try {
					// Receive Server Response
					byte[] byteData = receiveData(scan);
					if(byteData == null) continue;
					String responseData = new String(byteData);
					doProcess(responseData);
					LOGGER.info("Server Response = " + responseData.trim());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}*/

	@Override
	public String doProcess(String message) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method receives the Server Response
	 */
/*	public byte[] receiveData(Socket remoteClient) throws Exception {
		try(Scanner scan = new Scanner( new BufferedInputStream(socket.getInputStream()))) {			
			while (true)
			{
				if(! is.hasNextLine()) return null;
				String inputData = scan.nextLine();
				doProcess(inputData);
			}
						
			byte[] inputData = new byte[1024];
			is.read(inputData);
			return inputData;
		} catch (Exception exception) {
			throw exception;
		}
	}*/

	/**
	 * Method used to Send Request to Server
	 */
/*	public void sendData(Socket remoteSocket, byte[] byteData) throws Exception {
		try {
			DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			os.write(byteData);
			os.flush();
		} catch (Exception exception) {
			throw exception;
		}
	}*/

	private void updateTcpModel(TcpConnectionModel tcpClient/*, InputStream is, DataOutputStream os*/) {
/*		tcpClient.setDis(is);
		tcpClient.setDos(os);*/
		this.tcpClient = tcpClient;
	}

	public TcpConnectionModel getTcpClient() {
		return tcpClient;
	}

	public void setTcpClient(TcpConnectionModel tcpClient) {
		this.tcpClient = tcpClient;
	}
}