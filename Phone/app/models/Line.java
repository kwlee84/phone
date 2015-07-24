package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;

import com.avaje.ebean.Model;

@Entity
public class Line extends Model {

	@Id
	private Long id;
	
	@Length(max=4)
	@Column(name="number_1")
	private String number1;
	@Length(max=4)
	@Column(name="number_2")
	private String number2;
	@Length(max=4)
	@Column(name="number_3")
	private String number3;
	
	private String usim;
	/** 가입일 */
	private Date joinDate;
	/** 요금제변경가능일 */
	private Date keepPaySystemDate;
	/** 해지가능일 */
	private Date dutyPeriodDate;
	/** 해지일 */
	private Date cancelDate;
	/** PB 지급방식 */
	private PayBackStyle payBackStyle;
	/** 할부중도완납가능일 */
	private Date keepInstallmentDate;
	/** PB 입금예정일*/
	private Date payBackDate;
	/** 할부금 완납여부 */
	private boolean payInstallmentYn;
	/** 페이백 확인여부 */
	private boolean checkPaybackYn;
	@OneToOne
	private Business businessInfo;
	/** 납부계좌 */
	@ManyToOne
	private Account account;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber1() {
		return number1;
	}
	public void setNumber1(String number1) {
		this.number1 = number1;
	}
	public String getNumber2() {
		return number2;
	}
	public void setNumber2(String number2) {
		this.number2 = number2;
	}
	public String getNumber3() {
		return number3;
	}
	public void setNumber3(String number3) {
		this.number3 = number3;
	}
	public String getUsim() {
		return usim;
	}
	public void setUsim(String usim) {
		this.usim = usim;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getKeepPaySystemDate() {
		return keepPaySystemDate;
	}
	public void setKeepPaySystemDate(Date keepPaySystemDate) {
		this.keepPaySystemDate = keepPaySystemDate;
	}
	public Date getDutyPeriodDate() {
		return dutyPeriodDate;
	}
	public void setDutyPeriodDate(Date dutyPeriodDate) {
		this.dutyPeriodDate = dutyPeriodDate;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public PayBackStyle getPayBackStyle() {
		return payBackStyle;
	}
	public void setPayBackStyle(PayBackStyle payBackStyle) {
		this.payBackStyle = payBackStyle;
	}
	public Date getKeepInstallmentDate() {
		return keepInstallmentDate;
	}
	public void setKeepInstallmentDate(Date keepInstallmentDate) {
		this.keepInstallmentDate = keepInstallmentDate;
	}
	public Date getPayBackDate() {
		return payBackDate;
	}
	public void setPayBackDate(Date payBackDate) {
		this.payBackDate = payBackDate;
	}
	public boolean isPayInstallmentYn() {
		return payInstallmentYn;
	}
	public void setPayInstallmentYn(boolean payInstallmentYn) {
		this.payInstallmentYn = payInstallmentYn;
	}
	public boolean isCheckPaybackYn() {
		return checkPaybackYn;
	}
	public void setCheckPaybackYn(boolean checkPaybackYn) {
		this.checkPaybackYn = checkPaybackYn;
	}
	public Business getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(Business businessInfo) {
		this.businessInfo = businessInfo;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
