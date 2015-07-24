package models;

import java.util.Arrays;
import java.util.List;

public enum PayBackStyle {
	//
	payback("페이백"),
	payinkind("대납");
	
	private String krName;
	
	private PayBackStyle(String krName) {
		this.krName = krName;
	}
	
	public String getKrName() {
		return this.krName;
	}
	
	public static List<PayBackStyle> valuesAsList() {
		return Arrays.asList(PayBackStyle.values());
	}
	
}
