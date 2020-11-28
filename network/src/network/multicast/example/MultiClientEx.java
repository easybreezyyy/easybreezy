package network.multicast.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 사용자가 입력한 메시지를 서버에 전송
 *
 */

public class MultiClientEx {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private BufferedReader keyboard;

	public MultiClientEx() {
		try {
			socket = new Socket("localhost", 4000);
			System.out.println("Server Connect Success");
			
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			MultiClientThread t = new MultiClientThread(in);
			t.start();

			while (true) {
				String text = keyboard.readLine();
				out.println(text);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MultiClientEx();
	}

}
