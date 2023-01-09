package model;

public class Brand {

	protected String BrandName;
	protected int BrandID;

	public Brand(int brandID, String brandName) {
		super();
		this.BrandID = brandID;
		this.BrandName = brandName;
	}

	public int getBrandID() {
		return BrandID;
	}

	public void setBrandID(int brandID) {
		this.BrandID = brandID;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		this.BrandName = brandName;
	}
	
	
}
