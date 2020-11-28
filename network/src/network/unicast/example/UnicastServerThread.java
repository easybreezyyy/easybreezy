package network.unicast.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 각각의 클라이언트와 연결된 소켓객체를 가지고 있으며, 
 * 클라이언트와 접속을 유지하고 통신을 담당한다.
 *
 */
public class UnicastServerThread extends Thread {

	//서버로 부터 전달된 소켓(클라이언트와 연결된)을 저장
	Socket s = null;
	BufferedReader br = null;
	BufferedWriter bw = null;

	//객체 생성시 소켓을 전달받아서 멤버변수에 저장하는 생성자
	public UnicastServerThread(Socket s) {
		this.s = s;
	}
	
	//run() 오버라이딩
	public void run() {
		//스트림 연결
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
			while(true) {
				//클라이언트로부터 읽어들이고
				String msg = br.readLine();
				System.out.println(msg);
				
				//클라이언트한테 다시 쏴주고
				bw.write(msg+"\n");
				bw.flush();
			}
		}catch (IOException e) {
			InetAddress ip = s.getInetAddress();
			String address = ip.getHostAddress();
			System.out.println(address + "와의 연결이 끊어졌습니다.");
		}
	}
	
	
	

}
