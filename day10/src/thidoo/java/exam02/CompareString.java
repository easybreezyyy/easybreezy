package thidoo.java.exam02;

public class CompareString {
	public static void main(String[] args) {
		
		String str = new String("Star");
		String str2 = new String("Star");
		
		System.out.println("주소값 비교 : "+(str==str2));	//생성한 객체의 주소값을 비교 =>false
		System.out.println("해시코드 비교 : "+(str.hashCode()==str2.hashCode())); //true
		//값이 같으면 wrapper class에서는 해시코드가 같게 된다.
		
		String str3 = "Star";
		String str4 = "Star";
		System.out.println("주소값 비교 : "+(str3==str4));	//예외적으로 true
		System.out.println("해시코드 비교 : "+(str3.hashCode()==str4.hashCode())); //true
		
		//이렇게 짜증나니까 그냥 String 비교할 때는 .equals()를 사용하자^^!!! 라는 결론~
		 
	}
}
