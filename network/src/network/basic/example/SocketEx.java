package network.basic.example;

import java.net.Socket;

public class SocketEx {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 3000);
	}

}
