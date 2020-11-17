package quiz.phone;

/**
[조건1] - 클래스 이름은 CellPhone이다. 
- 전화번호를 의미하는 number라는 String형태의 private 멤버변수가 있다. 
- 멤버변수에 대한 getter와 setter메서드를 만든다. 
- 반환형과 인자가 없는 음성통화를 할 수 있는 call()이라는 메서드가 있다. 
[조건2] - 클래스 이름은 Phone3G이다. 
- CellPhone이라는 클래스를 상속받는다. 
- call() 메서드를 오버라이드 하여 영상통화를 할 수 있도록 한다. 
[조건3] - 프로그램을 실행 시킬 수 있는 main() 메서드를 가지고 있는 메인클래스를 만든다. 
- 아래와 같이 실행될 수 있도록 main()의 내용을 구성한다. 
- 단 다형성과 if문 super를 이용하도록 할 것. [실행예시]

1. 음성통화
2. 영상통화
메뉴선택 : 
1번 선택 시 : 음성통화를 합니다. 2번 선택 시 : 영상통화를 합니다.

 */

public class CellPhone {
	
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	void call() {
		System.out.println("음성통화를 합니다.");
	}

}
