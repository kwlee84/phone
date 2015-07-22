package kr.bartwars.phone.entity.domain;

import java.util.Date;

public class Line {

	private Long id;
	
	private String number1;
	private String number2;
	private String number3;
	
	private String usim;
	private Date joinDate;
	private Date dutyPeriodDate;
	private Date keepPaySystemDate;
	private Date cancelDate;
	private String paySystem;
	private Date keepInstallmanetDate;
	private Date payBack;
	private boolean useYn;
	private Account account;
	private boolean payInstallmentYn;
	private boolean checkPaybackYn;
	private Business businessInfo;
	
	
	
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
	public Date getDutyPeriodDate() {
		return dutyPeriodDate;
	}
	public void setDutyPeriodDate(Date dutyPeriodDate) {
		this.dutyPeriodDate = dutyPeriodDate;
	}
	public Date getKeepPaySystemDate() {
		return keepPaySystemDate;
	}
	public void setKeepPaySystemDate(Date keepPaySystemDate) {
		this.keepPaySystemDate = keepPaySystemDate;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getPaySystem() {
		return paySystem;
	}
	public void setPaySystem(String paySystem) {
		this.paySystem = paySystem;
	}
	public Date getKeepInstallmanetDate() {
		return keepInstallmanetDate;
	}
	public void setKeepInstallmanetDate(Date keepInstallmanetDate) {
		this.keepInstallmanetDate = keepInstallmanetDate;
	}
	public Date getPayBack() {
		return payBack;
	}
	public void setPayBack(Date payBack) {
		this.payBack = payBack;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Business getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(Business businessInfo) {
		this.businessInfo = businessInfo;
	}
	
}
