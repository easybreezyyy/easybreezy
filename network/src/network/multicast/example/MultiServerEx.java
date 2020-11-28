package network.multicast.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ��� Ŭ���̾�Ʈ�� �����û�� �޾Ƽ� ������ �����ϰ�,
 * ������ �����ϱ� ���� Thread��ü�� �����Ѵ�.
 * ������ ������ ��ü�� Collection(ArrayList)���� �����Ѵ�.
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
