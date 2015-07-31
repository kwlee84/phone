package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Account extends Model {
	//
	private static Finder<String, Account> finder = new Finder<String, Account>(Account.class);
	
    @Id
	private String accountNumber;
	private String bank;
	private String name;
	
	public static Account find(String id) {
		return finder.byId(id);
	}
	
	public static List<Account> findAll() {
		return finder.all();
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
