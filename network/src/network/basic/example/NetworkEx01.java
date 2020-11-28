package network.basic.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkEx01 {
	public static void main(String[] args) {
		try {
			InetAddress ipInfo1 = InetAddress.getByName("www.s-e-o.co.kr");
			//get IP address
			String ip = ipInfo1.getHostAddress();
			System.out.print("IP Address : " + ip);
			System.out.println();
			
			//get all Objects from www.s-e-o.co.kr
			//�� �ּҿ� �ش��ϴ� IP�ּ� �� �������� -> Ŭ���尡 �ߴ��ؼ� ���� 1,2���ۿ� �ȳ��´�.
			InetAddress[] ipArray = InetAddress.getAllByName("www.s-e-o.co.kr");
			for(InetAddress tempip : ipArray)
				System.out.println(tempip);
			
			//current computer's ip
			InetAddress myHost = InetAddress.getLocalHost();
			System.out.print("host(me) : " + myHost.getHostName());
			System.out.println();
			System.out.print("host IP (my IP) : " + myHost.getHostAddress());
		}catch(UnknownHostException e) {
			//when mismatching host name & host
			e.printStackTrace();
		}

	}

}
