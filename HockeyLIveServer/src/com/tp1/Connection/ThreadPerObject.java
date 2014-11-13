package com.tp1.Connection;

import java.net.Socket;


public class ThreadPerObject {
	private DataManagement dataManager;
	private Socket handler = null;
	private String data = "";

	public ThreadPerObject(Socket handler, DataManagement dataManager, String data) {
		this.handler = handler;
		this.dataManager = dataManager;
		this.data = data;
	}
	
	public void CreateThreadPerObject() {
			// Manage the data
			dataManager.manageData(handler, data);
	}
	
}
