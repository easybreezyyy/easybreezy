package network.multicast.example;

import java.io.BufferedReader;
import java.io.IOException;
/**
 * 서버가 전송한 메시지를 수신하기 위한 클래스
 *
 */
public class MultiClientThread extends Thread {
	private BufferedReader in;
	
	public MultiClientThread(BufferedReader in) {
		this.in = in;
	}
	
	public void run() {
		try {
			while(true) {
				String text = in.readLine();
				System.out.println("Message : " + text);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
