package quiz.awt.event;

public class MemberVO {
	
	private String name;
	private String id;
	private String number;
	private String gender;
	private String hobby;
	
	
	public MemberVO () {}

	public MemberVO(String name, String id, String number, String gender, String hobby) {
		super();
		this.name = name;
		this.id = id;
		this.number = number;
		this.gender = gender;
		this.hobby = hobby;
	}
	
//	public void memberinfo() {
//		
//	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	
	
	
	
	
	
	
}
