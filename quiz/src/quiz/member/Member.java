package quiz.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Member {

	private String name;
	private int age;
	private String number;
	private String addr;
	private String searchName = null;
	private int idx;

	Vector<Member> vc = new Vector<Member>();

	public Member() {}

	public Member(String name, int age, String number, String addr) {
		this.name = name;
		this.age = age;
		this.number = number;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "�̸� :" + name + "  ���� :" + age + "  ��ȭ:" + number + "  �ּ� :" + addr;
	}

	public int menu() throws IOException {
		System.out.println("***************");
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ���˻�");
		System.out.println("3. ��������");
		System.out.println("4. ȸ��Ż��");
		System.out.println("5. ��ü���");
		System.out.println("6. ���α׷� ����");
		System.out.println("***************");
		System.out.print("�޴����� : ");
		int menu = System.in.read() - 48;
		System.in.read(); System.in.read(); // ���� ó��
		return menu;
	}

	//ȸ������
	public void register() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("�̸� : ");
		name = br.readLine();
		System.out.print("���� : ");
		age = Integer.parseInt(br.readLine());
		System.out.print("��ȭ : ");
		number = br.readLine();
		System.out.print("�ּ� : ");
		addr = br.readLine();

		vc.add(new Member(name, age, number, addr));
		System.out.println("���������� �����ϼ̽��ϴ�.");

	}

	//�˻�
	public void search() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("ã���ô� ȸ�� �̸��� �Է����ּ��� : ");
		this.searchName = br.readLine();

		for (int i = 0; i < vc.size(); i++) {
			if (searchName.equals(vc.elementAt(i).getName())) {
				System.out.println("�˻��� �����Ͽ����ϴ�.");
				System.out.println();
				this.idx = i;
				break;
			}
		}
	}

	//����
	public void edit() throws IOException {
		if (this.searchName != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("1. ����ó ����");
			System.out.println("2. �ּ� ����");
			System.out.println("0. ���� ���");
			System.out.print("�޴� ���� : ");
			int menu = System.in.read() - 48;
			System.in.read(); System.in.read(); // ���� ó��

			switch (menu) {
			
			case 1:
				System.out.println(vc.elementAt(idx).getName() + "���� ������ �����մϴ�.");
				System.out.print("������ ����ó : ");
				vc.elementAt(idx).setNumber(br.readLine());
				System.out.println("���������� �����Ͽ����ϴ�.");
				System.out.println("����� ����ó : " + vc.elementAt(idx).getNumber());
				break;
			case 2:
				System.out.println(vc.elementAt(idx).getName() + "���� ������ �����մϴ�.");
				System.out.print("������ �ּ� : ");
				vc.elementAt(idx).setAddr(br.readLine());
				System.out.println("���������� �����Ͽ����ϴ�.");
				System.out.println("����� �ּ��� : " + vc.elementAt(idx).getAddr());
				break;
			case 0:
				System.out.println(vc.elementAt(idx).getName() + "���� ������ ����մϴ�.");
			}// end switch menu
		} else
			System.out.println("2�� �޴��� �˻� ���� ���ּ���.");
	}

	//����
	public void delete() {
		if (this.searchName != null) {
			System.out.print(vc.elementAt(idx).getName() + "���� ������ �����Ͻðڽ��ϱ�? (y/n) ");
			try {
				char answer = (char) System.in.read();
				System.in.read(); System.in.read(); // ����ó��
				if (answer == 'y' || answer == 'Y') {
					System.out.println(vc.elementAt(idx).getName() + "���� ������ �����մϴ�.");
					vc.removeElementAt(idx);
				} else 
					System.out.println(vc.elementAt(idx).getName() + "���� ���� ������ ����մϴ�.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("2�� �޴��� �˻� ���� ���ּ���.");
	}

	//��ü��ȸ
	public void list() {
		for(Member mb : vc)
			System.out.println(mb);
	}
	
	//���α׷�����
	public void exit() {
		System.out.print("������ �����Ͻðڽ��ϱ�? (y/n)");
		try {
			char answer = (char) System.in.read();
			System.in.read(); System.in.read(); // ����ó��
			if (answer == 'y' || answer == 'Y') {
				System.out.println("�ý����� �����մϴ�.");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
