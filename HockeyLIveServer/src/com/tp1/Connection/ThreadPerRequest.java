package com.tp1.Connection;

import java.io.IOException;
import java.net.Socket;


public class ThreadPerRequest extends Thread {
	private DataManagement dataManager;
	private Socket handler = null;
	private String data = "";

	public ThreadPerRequest(Socket handler, DataManagement dataManager, String data) {
		this.handler = handler;
		this.dataManager = dataManager;
		this.data = data;
	}

	@Override
	public void run() {
		try {
			// Manage the data
			dataManager.manageData(handler, data);
			
			handler.close();
		} catch (IOException e) {
			System.out.println("SocketException " + e.toString());
		}
	}
}
