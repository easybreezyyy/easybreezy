package network.multicast.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 각각의 클라이언트와 연결된 소켓을 가지고 있으며,
 * 클라이언트와 접속을 유지하고 통신을 담당한다.
 * 특정 클라이언트가 전송한 메시지를 수신해서 모든 클라이언트에게 전송하는 broadcasting이 구현되어야 한다.
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
				broadcasting(ipAddr + "와 연결이 끊어졌습니다.");
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
