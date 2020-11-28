package network.echoserver.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public EchoServer() {
		try {
			ServerSocket ss = new ServerSocket(3000);
			System.out.println("Server runs now");
			Socket s = ss.accept();
			System.out.println("Server starts waiting...");
			
			InetAddress clientip = s.getInetAddress();
			String ip = clientip.getHostAddress();
			System.out.println(ip + " is connected");
			
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			
			String message = br.readLine();
			System.out.println("Message : " + message);
			
			bw.write("Welcome " + ip + "!\n");
			bw.flush();
			
		}catch (IOException e) {
			System.out.println("this is already in use.");
		}
	}
	
	
	
	public static void main(String[] args) {
		new EchoServer();
	}

}
