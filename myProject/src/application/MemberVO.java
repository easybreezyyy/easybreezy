package application;

public class MemberVO {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String addr;
	private String card;
	
	public MemberVO() {}
	
	public MemberVO(String id, String password, String name, String phone, String addr, String card) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.card = card;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone + ", addr="
				+ addr + ", card=" + card + "]";
	}
	
	
	
}
