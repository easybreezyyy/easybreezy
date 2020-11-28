package network.basic.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketEx {

	public static void main(String[] args) throws IOException {

		try {
			ServerSocket ss = new ServerSocket(3000); // 3000번 포트를 생성
			while (true) {
				Socket s = ss.accept(); // 대기
				System.out.println("서버 대기중...");

				// 누군가 접속하면
				InetAddress iaddr = s.getInetAddress();
				String uip = iaddr.getHostAddress();
				System.out.println(uip + "님 접속");
			}
		} catch (UnknownHostException e) {
			System.out.println("can't find Host");
			e.printStackTrace();
		}
	}

}
