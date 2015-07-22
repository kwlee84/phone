package kr.bartwars.phone.entity.domain;

public class Business {
	
	private Long id;
	private Integer costOfMaintenace;
	private Integer sellingPrice;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCostOfMaintenace() {
		return costOfMaintenace;
	}
	public void setCostOfMaintenace(Integer costOfMaintenace) {
		this.costOfMaintenace = costOfMaintenace;
	}
	public Integer getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public Integer getProfit() {
		return this.sellingPrice - this.costOfMaintenace;
	}
}
