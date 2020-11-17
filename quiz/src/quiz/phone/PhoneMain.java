package quiz.phone;

import java.io.IOException;

public class PhoneMain {

	public static void main(String[] args) throws IOException{
		
		
		boolean flag = true;
		int menu;
		char exit;
		
		while(flag) {
			CellPhone cp = new CellPhone();
			System.out.println("1. 음성통화");
			System.out.println("2. 영상통화");
			System.out.println("0. 종료");
			System.out.print("메뉴를 선택해주세요 : ");
			menu = System.in.read()-48;
			System.in.read(); System.in.read(); //엔터처리
			
			if(menu == 1) {
				cp.call();
				System.out.println();
			}else if(menu == 2) {
				cp = new Phone3G();		//다형성(부모타입에 자식객체 대입)
				cp.call();
				System.out.println();
			}else if(menu == 0) {
				System.out.print("정말로 종료하시겠습니까? (y/n)");
				exit = (char) System.in.read();
				System.in.read(); System.in.read(); //엔터처리
				if(exit == 'y' || exit == 'Y') {
					System.out.println("종료합니다.");
					System.exit(0);
				}else {
					System.out.println("종료를 취소하셨습니다.");
					continue;
				}
			}else{
				System.out.println("없는 메뉴입니다. 메뉴를 다시 선택해주세요.");
				continue;
			}//end select menu
		}//end while

	}//end main

}
