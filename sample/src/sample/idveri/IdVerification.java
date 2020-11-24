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
		int[] arr = new int[13];	//�ֹι�ȣ
		double hap = 0;
		double total;
		boolean flag = true;
		double multi = 2.0f;
		String sex = "����";
		String birthPlace = null;
		int year = 0;
		
		System.out.print("�̸� : ");
		name = br.readLine();
		
		while(flag) {
			System.out.print("�ֹι�ȣ : ");
			for (int i=0; i<arr.length;i++){
				arr[i] = System.in.read()-48;
			}System.in.read(); System.in.read(); //����ó��
			
			for(int i=0; i<12; i++){
				if(i == 8)
					multi = 2.0f;
				hap += arr[i]*multi;
				multi++;
			}//end for multiply
			
			double temp = 11.0f * (int)(hap/11.0f) + 11.0f - hap;
			total = temp - 10.0f * ((int)(temp/10.0f));	//������� ����
	
			if((int)total == arr[12]){	//�ֹι�ȣ�� �´ٸ�
				if(arr[6]%2 == 1 || arr[6] == 0)		//��������
					sex = "����";
				
				switch(arr[6]) {
				case 1 :
				case 2 : 
					year = 1900; break;
				case 3 :
				case 4 : 
					year = 2000; break;
				default : 
					year = 1800; 
				}//���� ����
				
				year = year + arr[0] *10 + arr[1]; 
				int month = arr[2]*10 + arr[3]; 
				int day = arr[4]*10 + arr[5];
				int age = 2020 - year;
				
				switch(arr[7]) {
				case 0: 
					birthPlace = "����";
					break;
				case 1: 
					birthPlace = "���,��õ";
					break;
				case 2: 
					birthPlace = "����";
					break;
				case 3: 
					birthPlace = "���";
					break;
				case 4: 
					birthPlace = "�泲";
					break;
				case 5: 
					birthPlace = "����";
					break;
				case 6: 
					birthPlace = "����";
					break;
				case 7: 
					birthPlace = "���";
					break;
				case 8: 
					birthPlace = "�泲";
					break;
				case 9: 
					birthPlace = "����";
					break;
				}
				
				System.out.println(name + "���� �������� �м� ���");
				System.out.printf("������� : %d�� %d�� %d��\n",year,month,day);
				System.out.println("���� : " + sex);
				System.out.println("���� : ��" + age + "��");
				System.out.println("��� ���� : " + birthPlace);
				flag = false;
				
			}else{
				System.out.println("�߸��� �ֹι�ȣ�Դϴ�.");
				continue;
			}//end if
			
		}//end while

	}//end main
}
