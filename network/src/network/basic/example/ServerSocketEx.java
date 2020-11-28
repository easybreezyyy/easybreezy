package network.basic.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketEx {

	public static void main(String[] args) throws IOException {

		try {
			ServerSocket ss = new ServerSocket(3000); // 3000�� ��Ʈ�� ����
			while (true) {
				Socket s = ss.accept(); // ���
				System.out.println("���� �����...");

				// ������ �����ϸ�
				InetAddress iaddr = s.getInetAddress();
				String uip = iaddr.getHostAddress();
				System.out.println(uip + "�� ����");
			}
		} catch (UnknownHostException e) {
			System.out.println("can't find Host");
			e.printStackTrace();
		}
	}

}
