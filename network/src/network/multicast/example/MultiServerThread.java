package network.multicast.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ������ Ŭ���̾�Ʈ�� ����� ������ ������ ������,
 * Ŭ���̾�Ʈ�� ������ �����ϰ� ����� ����Ѵ�.
 * Ư�� Ŭ���̾�Ʈ�� ������ �޽����� �����ؼ� ��� Ŭ���̾�Ʈ���� �����ϴ� broadcasting�� �����Ǿ�� �Ѵ�.
 *
 */
public class MultiServerThread extends Thread{

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<MultiServerThread> clientList;
	
	public MultiServerThread(ArrayList<MultiServerThread> clientList, Socket socket) {
		this.socket = socket;
		this.clientList = clientList;
	}

	public synchronized void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(true) {
				String msg = in.readLine();
				System.out.println(msg);
				broadcasting(msg);
			}
			
		}catch(IOException e) {
			clientList.remove(this);
			String ipAddr = socket.getInetAddress().getHostAddress();
			
			try {
				broadcasting(ipAddr + "�� ������ ���������ϴ�.");
			}catch(IOException ee) {}
		}
	}

	public void broadcasting(String msg) throws IOException{
		for(MultiServerThread t : clientList)
			t.sendMsg(msg);
	}

	public void sendMsg(String msg) throws IOException {
		out.println(msg);
		out.flush();
	}
	
}
