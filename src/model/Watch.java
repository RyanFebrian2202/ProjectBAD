package model;

public class Watch {
	
	private String watchId;
	private String watchName;
	private String watchBrand;
	private int watchPrice;
	private int watchStock;
	
	public Watch(String watchId, String watchName, String watchBrand, int watchPrice, int watchStock) {
		super();
		this.watchId = watchId;
		this.watchName = watchName;
		this.watchBrand = watchBrand;
		this.watchPrice = watchPrice;
		this.watchStock = watchStock;
	}

	public String getWatchId() {
		return watchId;
	}

	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}

	public String getWatchName() {
		return watchName;
	}

	public void setWatchName(String watchName) {
		this.watchName = watchName;
	}

	public String getWatchBrand() {
		return watchBrand;
	}

	public void setWatchBrand(String watchBrand) {
		this.watchBrand = watchBrand;
	}

	public int getWatchPrice() {
		return watchPrice;
	}

	public void setWatchPrice(int watchPrice) {
		this.watchPrice = watchPrice;
	}

	public int getWatchStock() {
		return watchStock;
	}

	public void setWatchStock(int watchStock) {
		this.watchStock = watchStock;
	}
	
	

}
