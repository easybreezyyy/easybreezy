package network.unicast.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UnicastClientEx {

	Socket s = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	BufferedReader keyboard = null;

	public UnicastClientEx() {
		try {
			s = new Socket("localhost", 4000);
		} catch (UnknownHostException e) {
			System.out.println("can't find any Server");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("can't connect with Server");
			System.exit(0);
		}
		
		try {
			keyboard = new BufferedReader(new InputStreamReader(System.in));		//써서
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));	//보내!
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));		//읽어와

			while (true) {
				System.out.print("Message : ");
				String msg = keyboard.readLine();

				bw.write(msg + "\n");
				bw.flush();

				String getMsg = br.readLine();
				System.out.println("Received message : " + getMsg);
			}
		} catch (IOException e) {
			System.out.println("Missing connection with Server");
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		new UnicastClientEx();
	}

}
