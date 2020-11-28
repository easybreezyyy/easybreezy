package network.multicast.example;

import java.io.BufferedReader;
import java.io.IOException;
/**
 * ������ ������ �޽����� �����ϱ� ���� Ŭ����
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
