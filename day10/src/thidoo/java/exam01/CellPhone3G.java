package thidoo.java.exam01;

public class CellPhone3G extends CellPhone{
	
	@Override	//이걸 달면 상속 받은 메서드 이름 다르면 에러로 알려줌~
	public void call() {
		System.out.println("영상 통화를 합니다.");
	}
	
	public static void main(String[] args) {
		CellPhone3G phone = new CellPhone3G();
		phone.call();
	}
	
}
