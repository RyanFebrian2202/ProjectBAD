package model;

public class Watch {
	
	private int WatchID;
	private String WatchName;
	private String WatchBrand;
	private int WatchPrice;
	private int WatchStock;
	
	public Watch(int watchID, String watchName, String watchBrand, int watchPrice, int watchStock) {
		super();
		WatchID = watchID;
		WatchName = watchName;
		WatchBrand = watchBrand;
		WatchPrice = watchPrice;
		WatchStock = watchStock;
	}

	public int getWatchID() {
		return WatchID;
	}

	public void setWatchID(int watchID) {
		WatchID = watchID;
	}
	public String getWatchName() {
		return WatchName;
	}
	public void setWatchName(String watchName) {
		WatchName = watchName;
	}
	public String getWatchBrand() {
		return WatchBrand;
	}
	public void setWatchBrand(String watchBrand) {
		WatchBrand = watchBrand;
	}
	public int getWatchPrice() {
		return WatchPrice;
	}
	public void setWatchPrice(int watchPrice) {
		WatchPrice = watchPrice;
	}
	public int getWatchStock() {
		return WatchStock;
	}
	public void setWatchStock(int watchStock) {
		WatchStock = watchStock;
	}
	
	
}
