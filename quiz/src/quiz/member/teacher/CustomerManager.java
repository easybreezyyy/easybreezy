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
 * 프로그램의 가장 기본이 되는 고객관리 프로그램. CMS(CustomerManageService)
 *
 */

public class CustomerManager {

	// 멤버
	private BufferedReader br;
	private boolean isLoop;
	private ArrayList<Customer> data;		//고객들을 담아둘 데이터배열
	private int position = -1;				//data에서 인덱스번호 역할을 해줄 멤버변수

	// 생성자
	public CustomerManager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		isLoop = true;
		data = new ArrayList<Customer>();

	} 		//디폴트생성자에 메서드마다 매번 필요한 것들을 넣어놓자! constructors do initialize members!!! 

	// 메서드
	/**
	 * 프로그램 시작시 화면에 초기메뉴를 보여주는 메서드
	 * @throws IOException
	 */
	public void showMenu() throws IOException {

		int menu = -1;
		while (isLoop) {
			System.out.println("1. 회원가입");
			System.out.println("2. 회원검색");
			System.out.println("3. 정보수정");
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 전체목록");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴선택 : ");
			
			try {				
				menu = Integer.parseInt(br.readLine());
			}catch (NumberFormatException nfe) {
				//menu = -1;		//혹시 모르니까~~~ 정확하게^^
			}

			switch (menu) {
			case 1 : addCustomer(); break;
			case 2 : searchCustomer(); break;
			case 3 : updateCustomer(); break;
			case 4 : deleteCustomer(); break;
			case 5 : display(); break;
			case 0 : stopProgram(); break;
			default : 
				System.err.println("메뉴입력 오류 : 메뉴를 확인하시고 다시 입력해주세요.");
				break;
			}
			System.out.println();
		} // end while
	}

	/**
	 * 프로그램을 종료시키는 메서드
	 * @throws IOException
	 */
	private void stopProgram() throws IOException {
		System.out.println();
		System.out.println("정말로 프로그램을 종료하시겠습니까? (y/n) : ");
		String result = br.readLine();
		
		if(result.equals("Y") || result.equals("y")) {
			System.out.println("프로그램을 종료합니다.");
			isLoop = false;
		}else {
			System.out.println("프로그램 종료를 취소합니다.");
			isLoop = true;		//안바꼈지만 혹시 모르니까~~~ 정확하게^^
		}
	}

	/**
	 * 전체 고객 리스트 출력하는 메서드 
	 */
	private void display() {
		System.out.println();
		System.out.println("전체 고객 목록 보기");
		for(Customer myCustomer : data)
			System.out.println(myCustomer.toString());
	}

	/**
	 * 고객 리스트에서 삭제하는 메서드
	 * @throws IOException
	 */
	private void deleteCustomer() throws IOException {
		System.out.println();
		if(position == -1) {
			System.out.println("고객 검색을 먼저 수행하세요.");
			return;
		}
		
		Customer myCustomer = data.get(position);
		System.out.print(myCustomer.getName() + "님의 정보를 삭제하시겠습니까? (y/n) : ");
		String result = br.readLine();
		
		if(result.equals("Y") || result.equals("y")) {
			System.out.println(myCustomer.getName() + "님의 정보를 성공적으로 삭제 하였습니다.");
			data.remove(position);
			position = -1;
		}else {
			System.out.println(myCustomer.getName() + "님의 정보삭제를 취소합니다.");
		}
		
	}

	/**
	 * 고객 정보 수정하는 메서드
	 * 반드시 2번 메뉴 회원 검색 선행 후 실행해야 합니다.
	 * @throws IOException
	 */
	private void updateCustomer() throws IOException {
		System.out.println();
		if(position == -1) {
			System.out.println("고객 검색을 먼저 수행해주세요^^");
			return;
		}
		
		Customer myCustomer = data.get(position);
		int menu = -1;		//여기에서의 정보수정 메뉴 (지역변수)
		boolean isStop = false;
		while(!isStop) {		//검색은 됐는데 메뉴 잘못 눌렀을 때 초기메뉴 안가려고 while로 감싸기
			System.out.println(myCustomer.getName() + "님의 정보수정");
			System.out.println("1. 연락처 수정");
			System.out.println("2. 주소 수정");
			System.out.println("0. 수정 취소");
			System.out.print("메뉴 선택 : ");
			
			try {
				menu = Integer.parseInt(br.readLine());
			}catch(NumberFormatException nfs){
				menu = -1;
			}
			
			switch(menu) {
			case 1:
				System.out.print("수정할 전화번호 : ");
				String tempPhone = br.readLine();
				myCustomer.setPhone(tempPhone);
				System.out.println(myCustomer.getName() + "님의 전화번호가 " 
				+ myCustomer.getPhone() + "으로 변경되었습니다.");
				isStop = true;
				break;
				
			case 2:
				System.out.print("수정할 전화번호 : ");
				String tempAddr = br.readLine();
				myCustomer.setAddr(tempAddr);
				System.out.println(myCustomer.getName() + "님의 주소가 " 
						+ myCustomer.getAddr() + "으로 변경되었습니다.");
				isStop = true;
				break;
				
			case 0 :
				System.out.println(myCustomer.getName() + "님의 정보 수정을 취소합니다.");
				isStop = true;
				break;
				
			default : 
				System.err.println("메뉴입력 오류 : 메뉴를 확인하시고 다시 입력해주세요.");
				isStop = false;
				break;
			}
		}
		
		
	}

	/**
	 * 회원 검색 메서드
	 * @throws IOException
	 */
	private void searchCustomer() throws IOException {
		System.out.println();
		System.out.print("찾으시는 고객 이름 : ");
		String name = br.readLine();
		
		position = -1;		//재검색시 에러 방지를 위해 초기화 해놓자.
		for(int i = 0; i<data.size(); i++) {
			Customer myCustomer = data.get(i);
			if(name.equals(myCustomer.getName())){
				position = i;
				System.out.println(name + "님의 정보 검색 성공!");
				break;
			}
		}
		
		if(position == -1) {
			System.out.println(name + "님은 저희 고객이 아닙니다. 회원 가입 바랍니다^^.");
		}
	}

	/**
	 * 회원가입 메서드
	 * 가입시 회원 목록(data)에 순서대로 저장된다.
	 * @throws IOException
	 */
	private void addCustomer() throws IOException {
		System.out.println();
		System.out.print("고객 이름 : ");
		String name = br.readLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(br.readLine());
		System.out.print("전화 : ");
		String phone = br.readLine();
		System.out.print("주소 : ");
		String addr = br.readLine();
		
		Customer myCustomer = new Customer(name, age, phone, addr);
		
		data.add(myCustomer);
		System.out.println(name + "님의 정보를 성공적으로 등록하였습니다.");
	}

	/**
	 * 프로그램 실행하는 메인 메서드
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
