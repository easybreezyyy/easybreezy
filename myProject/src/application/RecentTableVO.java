package application;

public class RecentTableVO {
	
	private Integer rentalnum;
	private String id;
	private String name;
	private String stylenum;
	private String status;
	private String address;
	
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
	public String getStylenum() {
		return stylenum;
	}
	public void setStylenum(String stylenum) {
		this.stylenum = stylenum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRentalnum() {
		return rentalnum;
	}
	public void setRentalnum(Integer rentalnum) {
		this.rentalnum = rentalnum;
	}
	public RecentTableVO() {}
	
	public RecentTableVO(Integer rentalnum, String id, String name, String stylenum, String status, String address) {
		this.rentalnum = rentalnum;
		this.id = id;
		this.name = name;
		this.stylenum = stylenum;
		this.status = status;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "RecentTableVO [rentalnum=" + rentalnum + ", id=" + id + ", name=" + name + ", stylenum=" + stylenum
				+ ", status=" + status + ", address=" + address + "]";
	}

}
