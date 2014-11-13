package com.tp1.Connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.tp1.serialization.SerializeToJson;

public class ServerUtils {
	
	public static void SerializeAndSendData(Object dataToSend, Socket handler) throws IOException {
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(handler.getOutputStream()));

	    SerializeToJson stj = new SerializeToJson(dataToSend);
	    String json = stj.toJson();
        
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
