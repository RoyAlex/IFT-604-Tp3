package com.tp1.Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.tp1.DAO.MatchDAO;
import com.tp1.dto.MessageType;
import com.tp1.library.Match;

public class Connexion {

	private final int MAX_USER_CONNECTION = 200000;
	private DataManagement dataManager = new DataManagement();
	private ExecutorService threadPool;
	private ExecutorService threadPoolTimer;

	public void InitListener() {
		try 
		{
			// Create Server Socket
			threadPool = Executors.newFixedThreadPool(10);	
			ServerSocket server = new ServerSocket(9090, 0, InetAddress.getByName(null));
			
			StartTimer();

			while (true) {
	            System.out.println("Waiting for a connection...");
	            
	            Socket socket = server.accept();
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    
	            // Read request
	            String line;
	            line = in.readLine();
	            
	            if (line != null)
	            {
    	            boolean isPost = line.startsWith("POST");
    	            int contentLength = 0;
    	            String data = "";
    	            
    	            // Header
    	            while (!(line = in.readLine()).equals("")) {
    	                System.out.println(line);
    	                if (isPost) {
    	                    final String contentHeader = "Content-Length: ";
    	                    if (line.startsWith(contentHeader)) {
    	                        contentLength = Integer.parseInt(line.substring(contentHeader.length()));
    	                    }
    	                }
    	            }
    	    
    	            // Body
    	            if (isPost) {
    	                int c = 0;
    	                for (int i = 0; i < contentLength; i++) {
    	                    c = in.read();
    	                    data += (char) c;
    	                }
    	            }
    	            
    	            System.out.println(data);
    	            
    	            // Remove "request:"
    	            data = data.substring(8);
    	            
    	            String str = data.substring(0, 1);
                    data = data.substring(1);
                    
                    if(MessageType.ThreadPerObject.getValue().equals(str))
                    {
                        // Create parallel line for each object
                        ThreadPerObject tpo = new ThreadPerObject(socket, dataManager, data);
                        tpo.CreateThreadPerObject();
                    }
                    if(MessageType.ThreadPerRequest.getValue().equals(str))
                    {
                        // Create parallel line of execution for Thread Per Request
                        
                        ThreadPerRequest tpr = new ThreadPerRequest(socket, dataManager, data);
                        threadPool.execute(tpr);
                    }
	            }
	        }
		} 
		catch (Exception e) 
		{
			System.out.println("Error in Connexion: " + e);
		}
	}
	
	/**
	 * Start the hockey timer for each match
	 */
	private void StartTimer()
	{
	    threadPoolTimer = Executors.newFixedThreadPool(10);
	    
	    MatchDAO matchDAO = new MatchDAO();
	    
	    for (Match match : matchDAO.listMatchs())
	    {                        
	        TimerThread tt = new TimerThread(match);
	        threadPoolTimer.execute(tt);
	    }
	}

}
