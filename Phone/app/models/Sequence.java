package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlUpdate;

@Entity
public class Sequence extends Model {
	//
	private static Finder<String, Sequence> finder = new Finder<String, Sequence>(Sequence.class);
	@Transient
	public static final String BUSINESS = "business";
	@Transient
	public static final String LINE = "line";
	@Transient
	public static final String PERSON = "person";
	@Transient
	public static final String ATTACHED_FILE = "attachedFile";
	@Transient
	private static final Long START_SEQUENCE = 1L;

	@Column(unique = true, nullable = false)
	private String sequenceName;
	private Long nextSequence;
	
	public Sequence(){
		//
	}
	
	private Sequence(String sequenceName) {
		this.sequenceName = sequenceName;
		this.nextSequence = START_SEQUENCE + 1;
	}
	
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public Long getNextSequence() {
		return nextSequence;
	}
	public void setNextSequence(Long nextSequence) {
		this.nextSequence = nextSequence;
	}
	
	public static Long getSequence(String sequenceName) {
		Sequence sequence = finder.where().eq("sequenceName", sequenceName).findUnique();
		if(sequence == null) {
			new Sequence(sequenceName).insert();
			return START_SEQUENCE;
		}
		Long sequeceNumber = sequence.getNextSequence();
		sequence.setNextSequence(sequeceNumber + 1);
		
		String sql = "update sequence set next_sequence = :sequenceNumber where sequence_name = :sequenceName";
		SqlUpdate update = Ebean.createSqlUpdate(sql);
		update.setParameter("sequenceNumber", sequeceNumber + 1);
		update.setParameter("sequenceName", sequenceName);
		
		Ebean.execute(update);
		
		return sequeceNumber;
	}
	
}
