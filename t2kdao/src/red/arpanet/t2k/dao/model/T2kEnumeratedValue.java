package red.arpanet.t2k.dao.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kStringValue
 *
 */
@Entity
@Table(name="t2k_enumerated_value")
@NamedQueries({
	//@NamedQuery(name="FindInviteByTokenAndEmail",query="select i from T2kInvite i where i.emailAddress = :email and i.inviteToken = :token")
})
@CopyrightArpanet
public class T2kEnumeratedValue implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="value_group", nullable=false)
	private int valueGroup;
	
	@Column(name="value", nullable=false)
	private String value;
	
	@Column(name="ordinal")
	private int ordinal;	

	public T2kEnumeratedValue() {
		super();
	}   
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   

	public int getValueGroup() {
		return valueGroup;
	}
	
	public void setValueGroup(int valueGroup) {
		this.valueGroup = valueGroup;
	}
	
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}   
	
	public int getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
   
}
