package application;

public class CollectTableVO {

	private String name;
	private String address;
	private String phone;
	private String stylenum;
	private int rentalnum;
	private int returnnum;
	
	
	public int getReturnnum() {
		return returnnum;
	}

	public void setReturnnum(int returnnum) {
		this.returnnum = returnnum;
	}

	public int getRentalnum() {
		return rentalnum;
	}

	public void setRentalnum(int rentalnum) {
		this.rentalnum = rentalnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStylenum() {
		return stylenum;
	}

	public void setStylenum(String stylenum) {
		this.stylenum = stylenum;
	}

	public CollectTableVO() {}

	@Override
	public String toString() {
		return "CollectTableVO [name=" + name + ", address=" + address + ", phone=" + phone + ", stylenum=" + stylenum
				+ ", rentalnum=" + rentalnum + ", returnnum=" + returnnum + "]";
	}
	
	

}
