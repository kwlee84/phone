package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Business extends Model{
	
	@Id
	private String id;
	/** 회선유지비용 */
	private Integer costOfMaintenace;
	/** 단말기 판매가 */
	private Integer sellingPrice;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
