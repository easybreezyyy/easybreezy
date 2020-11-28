package network.unicast.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UnicastServerEx {

	public UnicastServerEx() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(4000);
		} catch (IOException e) {
			System.out.println("this Port is already in use.");
			System.exit(0);
		}

		try {
			while (true) {	//Good server never shut down!
				System.out.println("Server runs now...");
				Socket s = ss.accept();

				//항시 대기중인 소켓을 쓰레드로 생성 후 가동
				UnicastServerThread thread = new UnicastServerThread(s);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new UnicastServerEx();
	}

}
