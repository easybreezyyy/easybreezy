package sample.idveri;

import java.io.*;
/**
 * Version Array
 * @author leeji
 *
 */
public class IdVerification {
public static void main(String[] args) throws IOException{

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name;
		int[] arr = new int[13];	//주민번호
		double hap = 0;
		double total;
		boolean flag = true;
		double multi = 2.0f;
		String sex = "여자";
		String birthPlace = null;
		int year = 0;
		
		System.out.print("이름 : ");
		name = br.readLine();
		
		while(flag) {
			System.out.print("주민번호 : ");
			for (int i=0; i<arr.length;i++){
				arr[i] = System.in.read()-48;
			}System.in.read(); System.in.read(); //엔터처리
			
			for(int i=0; i<12; i++){
				if(i == 8)
					multi = 2.0f;
				hap += arr[i]*multi;
				multi++;
			}//end for multiply
			
			double temp = 11.0f * (int)(hap/11.0f) + 11.0f - hap;
			total = temp - 10.0f * ((int)(temp/10.0f));	//여기까지 검증
	
			if((int)total == arr[12]){	//주민번호가 맞다면
				if(arr[6]%2 == 1 || arr[6] == 0)		//성별설정
					sex = "남자";
				
				switch(arr[6]) {
				case 1 :
				case 2 : 
					year = 1900; break;
				case 3 :
				case 4 : 
					year = 2000; break;
				default : 
					year = 1800; 
				}//연도 설정
				
				year = year + arr[0] *10 + arr[1]; 
				int month = arr[2]*10 + arr[3]; 
				int day = arr[4]*10 + arr[5];
				int age = 2020 - year;
				
				switch(arr[7]) {
				case 0: 
					birthPlace = "서울";
					break;
				case 1: 
					birthPlace = "경기,인천";
					break;
				case 2: 
					birthPlace = "강원";
					break;
				case 3: 
					birthPlace = "충북";
					break;
				case 4: 
					birthPlace = "충남";
					break;
				case 5: 
					birthPlace = "전북";
					break;
				case 6: 
					birthPlace = "전남";
					break;
				case 7: 
					birthPlace = "경북";
					break;
				case 8: 
					birthPlace = "경남";
					break;
				case 9: 
					birthPlace = "제주";
					break;
				}
				
				System.out.println(name + "님의 개인정보 분석 결과");
				System.out.printf("생년월일 : %d년 %d월 %d일\n",year,month,day);
				System.out.println("성별 : " + sex);
				System.out.println("나이 : 만" + age + "세");
				System.out.println("출생 지역 : " + birthPlace);
				flag = false;
				
			}else{
				System.out.println("잘못된 주민번호입니다.");
				continue;
			}//end if
			
		}//end while

	}//end main
}
