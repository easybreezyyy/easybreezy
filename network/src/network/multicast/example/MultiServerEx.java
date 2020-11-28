package network.multicast.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 모든 클라이언트의 연결요청을 받아서 소켓을 생성하고,
 * 소켓을 유지하기 위한 Thread객체를 생성한다.
 * 생성된 스레드 객체를 Collection(ArrayList)내에 저장한다.
 *
 */
public class MultiServerEx {

	private ArrayList<MultiServerThread> clientList = new ArrayList<MultiServerThread>();
	private ServerSocket server = null;

	public MultiServerEx() {
		try {
			server = new ServerSocket(4000);
			System.out.println("Server runs now...");
			while (true) {
				Socket socket = server.accept();
				InetAddress ip = socket.getInetAddress();
				String ipAddr = ip.getHostAddress();
				System.out.println(ipAddr + " is connected");

				MultiServerThread t = new MultiServerThread(clientList, socket);
				t.start();
				clientList.add(t);
			} // end while

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new MultiServerEx();
	}

}
