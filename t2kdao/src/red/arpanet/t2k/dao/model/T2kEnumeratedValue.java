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
	@NamedQuery(name="FindRootGroups",query="select v from T2kEnumeratedValue v where v.valueGroup = 0 and v.id <> 0"),
	@NamedQuery(name="FindGroupById",query="select v from T2kEnumeratedValue v where v.valueGroup = :id"),
	@NamedQuery(name="FindValueByEnumValue",query="select v from T2kEnumeratedValue v where v.enumValue = :enumValue",
		hints={@QueryHint(name="openjpa.hint.OptimizeResultCount", value="1")}),
	@NamedQuery(name="FindValueById",query="select v from T2kEnumeratedValue v where v.id = :valueId",
		hints={@QueryHint(name="openjpa.hint.OptimizeResultCount", value="1")})
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
	
	@Column(name="enum_value", nullable=false)
	private String enumValue;
	
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
	
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	public int getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
   
}
