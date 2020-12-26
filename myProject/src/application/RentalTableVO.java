package application;


public class RentalTableVO {
	
	private Integer rentalnum;
	private String rentalDate;
	private String returnDate;
	private String itemName;
	private String brand;
	private String status;
	
	public RentalTableVO() {}
	
	public RentalTableVO(Integer rentalnum, String rentalDate, String returnDate, String itemName, String brand,
			String status) {
		this.rentalnum = rentalnum;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.itemName = itemName;
		this.brand = brand;
		this.status = status;
	}

	public Integer getRentalnum() {
		return rentalnum;
	}
	public void setRentalnum(Integer rentalnum) {
		this.rentalnum = rentalnum;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RentalTableVO [rentalnum=" + rentalnum + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate
				+ ", itemName=" + itemName + ", brand=" + brand + ", status=" + status + "]";
	}
	
	
	
}
