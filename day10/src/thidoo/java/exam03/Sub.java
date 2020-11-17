package thidoo.java.exam03;

public class Sub extends Super{
	public Sub() {
		//super(); //생략 되어 있음.
		super(5);
		System.out.println("하위클래스 생성자");
	}

	
}

