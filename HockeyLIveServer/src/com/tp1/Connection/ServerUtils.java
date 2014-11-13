package com.tp1.Connection;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.gson.Gson;

public class ServerUtils {
	
	public static void SerializeAndSendData(Object dataToSend, Socket handler) throws IOException {
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(handler.getOutputStream()));
	    
	    Gson gson = new Gson();
	    String test = "Testing this one";
        String json = gson.toJson(test);
        
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK\r\n");
        builder.append("Content-Type: application/javascripts; charset=utf-8\r\n");
        builder.append("Access-Control-Allow-Origin: *\r\n");
        builder.append("Content-Length:" + json.length() + "\r\n\r\n");
        builder.append(json);
        out.write(builder.toString());
        
        out.flush();
        out.close();
	}

}
