package network.basic.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkEx02 {
/**
 * URLConnection Example
 * page 265
 */
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.s-e-o.co.kr");
			URLConnection uc = url.openConnection();
			uc.connect();
			String str = null;
			br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			while((str=br.readLine()) != null) {
				System.out.println(str);
			}
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {if(br != null)br.close();} catch(IOException e) {}
		}

	}

}
