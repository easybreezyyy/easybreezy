package network.echoserver.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientEx {

	public EchoClientEx() {
		try {
			Socket s = new Socket("localhost",3000);
			
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			
			bw.write("Hello, This is Client\n");
			bw.flush();
			
			String message = br.readLine();
			System.out.println("Message from Server : " + message);
			
		}catch (UnknownHostException e) {
			System.out.println("can't find the Server");
		}catch(IOException e ) {
			System.out.println("the Port number is not in use.");
		}
	}
	
	
	public static void main(String[] args) {
		new EchoClientEx();
	}

}
