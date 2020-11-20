package quiz.awt.event01;

import java.io.Serializable;

public class MemberVO implements Serializable{


	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private String num1;
	private String num2;
	private String num3;
	private String gender;
	private String hobby;

	public MemberVO() {}

	public MemberVO(String name, String id, String num1, String num2, String num3, String gender, String hobby) {
		this.name = name;
		this.id = id;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.gender = gender;
		this.hobby = hobby;
	}

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

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getNum3() {
		return num3;
	}

	public void setNum3(String num3) {
		this.num3 = num3;
	}

}
