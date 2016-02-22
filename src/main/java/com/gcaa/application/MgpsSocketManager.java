package com.gcaa.application;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcaa.tcp.model.TcpConnectionModel;
import com.gcaa.tcp.socket.MGPSConnectionSocket;
import com.gcaa.tcp.socket.MGPSSocketFactory;

@Component
public class MgpsSocketManager {

	@Autowired
	private MGPSSocketFactory socketFactory;

	private List<TcpConnectionModel> tcpClientConnections = new ArrayList<TcpConnectionModel>();
	private List<TcpConnectionModel> tcpServerConnections = new ArrayList<TcpConnectionModel>();
	
	private static final Logger LOGGER = Logger.getLogger(MgpsSocketManager.class);

	@PostConstruct
	public void initiateServerClient() {
		populateTcpClients();
		populateTcpServers();

		initializeSockets(tcpServerConnections);
		//initializeSockets(tcpClientConnections);
	}

	private void initializeSockets(List<TcpConnectionModel> tcpSocketModels) {

		if (tcpSocketModels.size() > 0) {
			for (final TcpConnectionModel tcpSocketModel : tcpSocketModels) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							MGPSConnectionSocket connectionSocket = socketFactory
									.getSocket(tcpSocketModel.getSocketType());
							connectionSocket.init(tcpSocketModel);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, tcpSocketModel.getSocketName()).start();
			}
		}
	}

	private TcpConnectionModel createEntity(String host, int port, String socketType, String socketName) {
		TcpConnectionModel tcpModel = new TcpConnectionModel();
		tcpModel.setHost(host);
		tcpModel.setPort(port);
		tcpModel.setSocketType(socketType);
		tcpModel.setSocketName(socketName);
		return tcpModel;
	}

	private List<TcpConnectionModel> populateTcpClients() {
		// Load clients from DB
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "AMHS-CLIENT"));
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "AFTN-CLIENT"));
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "FDPS-CLIENT"));

		return tcpClientConnections;
	}

	private List<TcpConnectionModel> populateTcpServers() {
		// Load clients from DB
		tcpServerConnections.add(createEntity("localhost", 5678, "SERVER", "MGPS-SERVER"));
		return tcpServerConnections;
	}
}

/*
 * if (tcpClientConnections.size() > 0) { for (final TcpConnectionModel
 * tcpClient : tcpClientConnections) { new Thread(new Runnable() {
 * 
 * @Override public void run() { // TODO Auto-generated method stub try { new
 * MgpsTcpClient().init(tcpClient); } catch (Exception e) {
 * LOGGER.error(e.getMessage()); e.printStackTrace(); } } }).start(); }
 * 
 * }
 */