package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kNativeLanguage
 *
 */
@Entity
@Table(name="t2k_native_language")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kNativeLanguage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="target_number")
	private int targetNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kSkill languageSkill;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}

	public T2kSkill getLanguageSkill() {
		return languageSkill;
	}

	public void setLanguageSkill(T2kSkill languageSkill) {
		this.languageSkill = languageSkill;
	}
	
}
