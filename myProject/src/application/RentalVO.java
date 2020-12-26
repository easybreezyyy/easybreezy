package application;

import java.util.Date;

public class RentalVO {
	private int rentalnum;
	private String id;
	private String stylenum;
	private Date rentaldate;
	private Date returndate;
	private String name;
	private String phone;
	private String address;
	private String status;
	
	public int getRentalnum() {
		return rentalnum;
	}
	public void setRentalnum(int rentalnum) {
		this.rentalnum = rentalnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStylenum() {
		return stylenum;
	}
	public void setStylenum(String stylenum) {
		this.stylenum = stylenum;
	}
	public Date getRentaldate() {
		return rentaldate;
	}
	public void setRentaldate(Date rentaldate) {
		this.rentaldate = rentaldate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public RentalVO() {}
	
	public RentalVO(int rentalnum, String id, String stylenum, Date rentaldate, Date returndate, String name,
			String phone, String address, String status) {
		this.rentalnum = rentalnum;
		this.id = id;
		this.stylenum = stylenum;
		this.rentaldate = rentaldate;
		this.returndate = returndate;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "RentalVO [rentalnum=" + rentalnum + ", id=" + id + ", stylenum=" + stylenum + ", rentaldate="
				+ rentaldate + ", returndate=" + returndate + ", name=" + name + ", phone=" + phone + ", address="
				+ address + ", status=" + status + "]";
	}
	
}
