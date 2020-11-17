package thidoo.java.exam01;

/*
 * 1. 음성통화
 * 2. 영상통화
 * 0. 종료
 * 메뉴선택 : 
 * 
 * 0 -> 정말로 종료하시겠습니까? (y/n) //n -> 다시 메뉴 선택 화면
 * 1 -> 음성통화를 합니다.
 * 2 -> 영상통화를 합니다.
 * 
 * 멤버는 private으로 은닉화하기 (getter/setter)
 * 기능은 메서드로!
 * 제어문 + 상속을 이용해서 프로그램 만들기
 * CellPhone (부모클래스)
 * ThreeGPhone (자식클래스)
 * PhoneTest 
 */

public class CellPhone {
	
	String model;
	String number;//전화번호
	int chord; //화음
	
	public void call() {
		System.out.println("음성 통화를 합니다.");
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getChord() {
		return chord;
	}
	public void setChord(int chord) {
		this.chord = chord;
	}

}
