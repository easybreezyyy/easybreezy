package quiz.member.teacher.vo;

/**
 * 고객정보를 기억하는 Value Object
 * @author leejisoo
 *
 */

public class Customer {
	//멤버
	private String name;
	private int age;
	private String phone;
	private String addr;
	
	
	//생성자
	public Customer() {}

	public Customer(String name, int age, String phone, String addr) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.addr = addr;
	}

	
	//메서드
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", phone=" + phone + ", addr=" + addr + "]";
	}
	
	
	
	
	
	
	
}
