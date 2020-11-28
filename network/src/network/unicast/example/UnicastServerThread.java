package network.unicast.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ������ Ŭ���̾�Ʈ�� ����� ���ϰ�ü�� ������ ������, 
 * Ŭ���̾�Ʈ�� ������ �����ϰ� ����� ����Ѵ�.
 *
 */
public class UnicastServerThread extends Thread {

	//������ ���� ���޵� ����(Ŭ���̾�Ʈ�� �����)�� ����
	Socket s = null;
	BufferedReader br = null;
	BufferedWriter bw = null;

	//��ü ������ ������ ���޹޾Ƽ� ��������� �����ϴ� ������
	public UnicastServerThread(Socket s) {
		this.s = s;
	}
	
	//run() �������̵�
	public void run() {
		//��Ʈ�� ����
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
			while(true) {
				//Ŭ���̾�Ʈ�κ��� �о���̰�
				String msg = br.readLine();
				System.out.println(msg);
				
				//Ŭ���̾�Ʈ���� �ٽ� ���ְ�
				bw.write(msg+"\n");
				bw.flush();
			}
		}catch (IOException e) {
			InetAddress ip = s.getInetAddress();
			String address = ip.getHostAddress();
			System.out.println(address + "���� ������ ���������ϴ�.");
		}
	}
	
	
	

}
