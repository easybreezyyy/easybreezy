package quiz.member.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import quiz.member.teacher.vo.Customer;

/**
 * 
 * @author leejisoo
 * @version 1.0
 * ���α׷��� ���� �⺻�� �Ǵ� ������ ���α׷�. CMS(CustomerManageService)
 *
 */

public class CustomerManager {

	// ���
	private BufferedReader br;
	private boolean isLoop;
	private ArrayList<Customer> data;		//������ ��Ƶ� �����͹迭
	private int position = -1;				//data���� �ε�����ȣ ������ ���� �������

	// ������
	public CustomerManager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		isLoop = true;
		data = new ArrayList<Customer>();

	} 		//����Ʈ�����ڿ� �޼��帶�� �Ź� �ʿ��� �͵��� �־����! constructors do initialize members!!! 

	// �޼���
	/**
	 * ���α׷� ���۽� ȭ�鿡 �ʱ�޴��� �����ִ� �޼���
	 * @throws IOException
	 */
	public void showMenu() throws IOException {

		int menu = -1;
		while (isLoop) {
			System.out.println("1. ȸ������");
			System.out.println("2. ȸ���˻�");
			System.out.println("3. ��������");
			System.out.println("4. ȸ��Ż��");
			System.out.println("5. ��ü���");
			System.out.println("0. ���α׷� ����");
			System.out.print("�޴����� : ");
			
			try {				
				menu = Integer.parseInt(br.readLine());
			}catch (NumberFormatException nfe) {
				//menu = -1;		//Ȥ�� �𸣴ϱ�~~~ ��Ȯ�ϰ�^^
			}

			switch (menu) {
			case 1 : addCustomer(); break;
			case 2 : searchCustomer(); break;
			case 3 : updateCustomer(); break;
			case 4 : deleteCustomer(); break;
			case 5 : display(); break;
			case 0 : stopProgram(); break;
			default : 
				System.err.println("�޴��Է� ���� : �޴��� Ȯ���Ͻð� �ٽ� �Է����ּ���.");
				break;
			}
			System.out.println();
		} // end while
	}

	/**
	 * ���α׷��� �����Ű�� �޼���
	 * @throws IOException
	 */
	private void stopProgram() throws IOException {
		System.out.println();
		System.out.println("������ ���α׷��� �����Ͻðڽ��ϱ�? (y/n) : ");
		String result = br.readLine();
		
		if(result.equals("Y") || result.equals("y")) {
			System.out.println("���α׷��� �����մϴ�.");
			isLoop = false;
		}else {
			System.out.println("���α׷� ���Ḧ ����մϴ�.");
			isLoop = true;		//�ȹٲ����� Ȥ�� �𸣴ϱ�~~~ ��Ȯ�ϰ�^^
		}
	}

	/**
	 * ��ü �� ����Ʈ ����ϴ� �޼��� 
	 */
	private void display() {
		System.out.println();
		System.out.println("��ü �� ��� ����");
		for(Customer myCustomer : data)
			System.out.println(myCustomer.toString());
	}

	/**
	 * �� ����Ʈ���� �����ϴ� �޼���
	 * @throws IOException
	 */
	private void deleteCustomer() throws IOException {
		System.out.println();
		if(position == -1) {
			System.out.println("�� �˻��� ���� �����ϼ���.");
			return;
		}
		
		Customer myCustomer = data.get(position);
		System.out.print(myCustomer.getName() + "���� ������ �����Ͻðڽ��ϱ�? (y/n) : ");
		String result = br.readLine();
		
		if(result.equals("Y") || result.equals("y")) {
			System.out.println(myCustomer.getName() + "���� ������ ���������� ���� �Ͽ����ϴ�.");
			data.remove(position);
			position = -1;
		}else {
			System.out.println(myCustomer.getName() + "���� ���������� ����մϴ�.");
		}
		
	}

	/**
	 * �� ���� �����ϴ� �޼���
	 * �ݵ�� 2�� �޴� ȸ�� �˻� ���� �� �����ؾ� �մϴ�.
	 * @throws IOException
	 */
	private void updateCustomer() throws IOException {
		System.out.println();
		if(position == -1) {
			System.out.println("�� �˻��� ���� �������ּ���^^");
			return;
		}
		
		Customer myCustomer = data.get(position);
		int menu = -1;		//���⿡���� �������� �޴� (��������)
		boolean isStop = false;
		while(!isStop) {		//�˻��� �ƴµ� �޴� �߸� ������ �� �ʱ�޴� �Ȱ����� while�� ���α�
			System.out.println(myCustomer.getName() + "���� ��������");
			System.out.println("1. ����ó ����");
			System.out.println("2. �ּ� ����");
			System.out.println("0. ���� ���");
			System.out.print("�޴� ���� : ");
			
			try {
				menu = Integer.parseInt(br.readLine());
			}catch(NumberFormatException nfs){
				menu = -1;
			}
			
			switch(menu) {
			case 1:
				System.out.print("������ ��ȭ��ȣ : ");
				String tempPhone = br.readLine();
				myCustomer.setPhone(tempPhone);
				System.out.println(myCustomer.getName() + "���� ��ȭ��ȣ�� " 
				+ myCustomer.getPhone() + "���� ����Ǿ����ϴ�.");
				isStop = true;
				break;
				
			case 2:
				System.out.print("������ ��ȭ��ȣ : ");
				String tempAddr = br.readLine();
				myCustomer.setAddr(tempAddr);
				System.out.println(myCustomer.getName() + "���� �ּҰ� " 
						+ myCustomer.getAddr() + "���� ����Ǿ����ϴ�.");
				isStop = true;
				break;
				
			case 0 :
				System.out.println(myCustomer.getName() + "���� ���� ������ ����մϴ�.");
				isStop = true;
				break;
				
			default : 
				System.err.println("�޴��Է� ���� : �޴��� Ȯ���Ͻð� �ٽ� �Է����ּ���.");
				isStop = false;
				break;
			}
		}
		
		
	}

	/**
	 * ȸ�� �˻� �޼���
	 * @throws IOException
	 */
	private void searchCustomer() throws IOException {
		System.out.println();
		System.out.print("ã���ô� �� �̸� : ");
		String name = br.readLine();
		
		position = -1;		//��˻��� ���� ������ ���� �ʱ�ȭ �س���.
		for(int i = 0; i<data.size(); i++) {
			Customer myCustomer = data.get(i);
			if(name.equals(myCustomer.getName())){
				position = i;
				System.out.println(name + "���� ���� �˻� ����!");
				break;
			}
		}
		
		if(position == -1) {
			System.out.println(name + "���� ���� ���� �ƴմϴ�. ȸ�� ���� �ٶ��ϴ�^^.");
		}
	}

	/**
	 * ȸ������ �޼���
	 * ���Խ� ȸ�� ���(data)�� ������� ����ȴ�.
	 * @throws IOException
	 */
	private void addCustomer() throws IOException {
		System.out.println();
		System.out.print("�� �̸� : ");
		String name = br.readLine();
		System.out.print("���� : ");
		int age = Integer.parseInt(br.readLine());
		System.out.print("��ȭ : ");
		String phone = br.readLine();
		System.out.print("�ּ� : ");
		String addr = br.readLine();
		
		Customer myCustomer = new Customer(name, age, phone, addr);
		
		data.add(myCustomer);
		System.out.println(name + "���� ������ ���������� ����Ͽ����ϴ�.");
	}

	/**
	 * ���α׷� �����ϴ� ���� �޼���
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerManager manager = new CustomerManager();
		try {
			manager.showMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// end main

}
