package quiz.member;

import java.io.IOException;

public class MemManager {

	public static void main(String[] args) throws IOException{
		
		Member mem = new Member();
		
		while(true) {
			switch(mem.menu()) {
			case 1: mem.register(); break;
			case 2: mem.search(); break;
			case 3: mem.edit(); break;
			case 4: mem.delete(); break;
			case 5: mem.list(); break;
			case 6 :mem.exit(); break;
			default : 
				System.err.println("�޴��Է� ���� : �޴��� Ȯ���Ͻð� �ٽ� �Է����ּ���.");
				break;
			}//end switch
			
		}//end while
		
	}//end main

}

