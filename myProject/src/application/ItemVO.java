package application;

/**
 * 상품 정보 클래스
 */
public class ItemVO {
	
	private String stylenum;
	private String itemname;
	private String brand;
	private int stock;
	private String imagepath;
	private int price;
	
	public ItemVO() {}
	
	public String getStylenum() {
		return stylenum;
	}
	public void setStylenum(String stylenum) {
		this.stylenum = stylenum;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public ItemVO(String stylenum, String itemname, String brand, int stock, String imagepath, int price) {
		this.stylenum = stylenum;
		this.itemname = itemname;
		this.brand = brand;
		this.stock = stock;
		this.imagepath = imagepath;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return stylenum;
	}
	
	
}
