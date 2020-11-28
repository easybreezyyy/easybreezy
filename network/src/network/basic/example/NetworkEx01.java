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
			//위 주소에 해당하는 IP주소 다 가져오기 -> 클라우드가 발달해서 요즘엔 1,2개밖에 안나온다.
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
