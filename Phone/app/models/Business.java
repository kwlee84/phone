package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Business extends Model{
	
	@Id
	private Long id;
	/** 회선유지비용 */
	private Integer costOfMaintenace;
	/** 단말기 판매가 */
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
