package application;

public class CustomerTableVO {
	
	private String id;
	private String name;
	private String phone;
	private String card;
	private String status;
	
	public CustomerTableVO() {}
	
	public CustomerTableVO(String id, String name, String phone, String card, String status) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.card = card;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
}
