package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;

@Entity
public class Line extends Model {

	private static Finder<String, Line> finder = new Finder<String, Line>(Line.class);
	
	@Id
	private String id;
	/** 명의자 */
	@ManyToOne
	private Person person;
	@Required
	@Column(name="number_1")
	@MaxLength(4)
	private String number1;
	@Required
	@Column(name="number_2")
	@MaxLength(4)
	private String number2;
	@Required
	@Column(name="number_3")
	@MaxLength(4)
	private String number3;
	
	@Required
	private String usim;
	/** 가입일 */
	@Required
	private Date joinDate;
	/** 요금제변경가능일 */
	@Required
	private Date keepPaySystemDate;
	/** 해지가능일 */
	@Required
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
	@OneToOne(cascade = CascadeType.ALL)
	private Business businessInfo;
	/** 납부계좌 */
	@ManyToOne
	private Account account;
	@OneToOne(cascade = CascadeType.ALL)
	private AttachedFile captureFile;
	
	public static Line find(String id) {
		return finder.byId(id);
	}
	
	public static List<Line> findAll() {
		return finder.all();
	}
	

	public static List<Line> findByPerson(String personId) {
		return finder.where().eq("person.id", personId).findList();
	}
	
	
	public static Line setUpdatedValues(String id, Line param) {
		//
		Line line = Line.find(id);
		line.setNumber1(param.getNumber1());
		line.setNumber2(param.getNumber2());
		line.setNumber3(param.getNumber3());
		line.setCancelDate(param.getCancelDate());
		line.setCheckPaybackYn(param.isCheckPaybackYn());
		line.setDutyPeriodDate(param.getDutyPeriodDate());
		line.setJoinDate(param.getJoinDate());
		line.setKeepInstallmentDate(param.getKeepInstallmentDate());
		line.setKeepPaySystemDate(param.getKeepPaySystemDate());
		line.setPayBackDate(param.getPayBackDate());
		line.setPayBackStyle(param.getPayBackStyle());
		line.setPayInstallmentYn(param.isPayInstallmentYn());
		line.setUsim(param.getUsim());
		if(line.getBusinessInfo() != null) {
			line.setBusinessInfo(param.getBusinessInfo());
		}
		if(param.getAccount() == null || param.getAccount().getAccountNumber().isEmpty()) {
			line.setAccount(null);
		} else {
			line.setAccount(param.getAccount());
		}
		if(param.getCaptureFile() != null) {
			param.getCaptureFile().insert();
			line.setCaptureFile(param.getCaptureFile());
		}
		if(line.getBusinessInfo() == null) {
			param.getBusinessInfo().insert();
			line.setBusinessInfo(param.getBusinessInfo());
		} else {
			line.getBusinessInfo().setCostOfMaintenace(param.getBusinessInfo().getCostOfMaintenace());
			line.getBusinessInfo().setSellingPrice(param.getBusinessInfo().getSellingPrice());
		}
		//setBusinessInfo(param.businessInfo);
		return line;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public AttachedFile getCaptureFile() {
		return captureFile;
	}
	public void setCaptureFile(AttachedFile captureFile) {
		this.captureFile = captureFile;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
