package model;

public class Watch {
	protected String WatchName, WatchBrand;
	protected Integer WatchID, WatchPrice, WatchStock;
	
	public Watch(Integer watchID, String watchName, String watchBrand, Integer watchPrice, Integer watchStock) {
		super();
		WatchID = watchID;
		WatchName = watchName;
		WatchBrand = watchBrand;
		WatchPrice = watchPrice;
		WatchStock = watchStock;
	}
	
	public Integer getWatchID() {
		return WatchID;
	}
	public void setWatchID(Integer watchID) {
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
	public Integer getWatchPrice() {
		return WatchPrice;
	}
	public void setWatchPrice(Integer watchPrice) {
		WatchPrice = watchPrice;
	}
	public Integer getWatchStock() {
		return WatchStock;
	}
	public void setWatchStock(Integer watchStock) {
		WatchStock = watchStock;
	}
	
	

}
