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
		return "이름 :" + name + "  나이 :" + age + "  전화:" + number + "  주소 :" + addr;
	}

	public int menu() throws IOException {
		System.out.println("***************");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원검색");
		System.out.println("3. 정보수정");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. 전체목록");
		System.out.println("6. 프로그램 종료");
		System.out.println("***************");
		System.out.print("메뉴선택 : ");
		int menu = System.in.read() - 48;
		System.in.read(); System.in.read(); // 엔터 처리
		return menu;
	}

	//회원가입
	public void register() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("이름 : ");
		name = br.readLine();
		System.out.print("나이 : ");
		age = Integer.parseInt(br.readLine());
		System.out.print("전화 : ");
		number = br.readLine();
		System.out.print("주소 : ");
		addr = br.readLine();

		vc.add(new Member(name, age, number, addr));
		System.out.println("성공적으로 가입하셨습니다.");

	}

	//검색
	public void search() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("찾으시는 회원 이름을 입력해주세요 : ");
		this.searchName = br.readLine();

		for (int i = 0; i < vc.size(); i++) {
			if (searchName.equals(vc.elementAt(i).getName())) {
				System.out.println("검색에 성공하였습니다.");
				System.out.println();
				this.idx = i;
				break;
			}
		}
	}

	//수정
	public void edit() throws IOException {
		if (this.searchName != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("1. 연락처 수정");
			System.out.println("2. 주소 수정");
			System.out.println("0. 수정 취소");
			System.out.print("메뉴 선택 : ");
			int menu = System.in.read() - 48;
			System.in.read(); System.in.read(); // 엔터 처리

			switch (menu) {
			
			case 1:
				System.out.println(vc.elementAt(idx).getName() + "님의 정보를 수정합니다.");
				System.out.print("수정할 연락처 : ");
				vc.elementAt(idx).setNumber(br.readLine());
				System.out.println("성공적으로 수정하였습니다.");
				System.out.println("변경된 연락처 : " + vc.elementAt(idx).getNumber());
				break;
			case 2:
				System.out.println(vc.elementAt(idx).getName() + "님의 정보를 수정합니다.");
				System.out.print("수정할 주소 : ");
				vc.elementAt(idx).setAddr(br.readLine());
				System.out.println("성공적으로 수정하였습니다.");
				System.out.println("변경된 주소지 : " + vc.elementAt(idx).getAddr());
				break;
			case 0:
				System.out.println(vc.elementAt(idx).getName() + "님의 수정을 취소합니다.");
			}// end switch menu
		} else
			System.out.println("2번 메뉴인 검색 먼저 해주세요.");
	}

	//삭제
	public void delete() {
		if (this.searchName != null) {
			System.out.print(vc.elementAt(idx).getName() + "님의 정보를 삭제하시겠습니까? (y/n) ");
			try {
				char answer = (char) System.in.read();
				System.in.read(); System.in.read(); // 엔터처리
				if (answer == 'y' || answer == 'Y') {
					System.out.println(vc.elementAt(idx).getName() + "님의 정보를 삭제합니다.");
					vc.removeElementAt(idx);
				} else 
					System.out.println(vc.elementAt(idx).getName() + "님의 정보 삭제를 취소합니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("2번 메뉴인 검색 먼저 해주세요.");
	}

	//전체조회
	public void list() {
		for(Member mb : vc)
			System.out.println(mb);
	}
	
	//프로그램종료
	public void exit() {
		System.out.print("정말로 종료하시겠습니까? (y/n)");
		try {
			char answer = (char) System.in.read();
			System.in.read(); System.in.read(); // 엔터처리
			if (answer == 'y' || answer == 'Y') {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
