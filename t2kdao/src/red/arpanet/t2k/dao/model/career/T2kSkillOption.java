package red.arpanet.t2k.dao.model.career;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import red.arpanet.t2k.dao.model.character.T2kSkillLevel;
import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kSkillOption
 *
 */
@Entity
@Table(name="t2k_skill_option")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kSkillOption implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="optional")
	private boolean optional;
	
	@ManyToOne
	private T2kSkillLevel skillLevel;
	
	@ManyToOne
	private T2kSkillLevel altSkillLevel;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public T2kSkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(T2kSkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public T2kSkillLevel getAltSkillLevel() {
		return altSkillLevel;
	}

	public void setAltSkillLevel(T2kSkillLevel altSkillLevel) {
		this.altSkillLevel = altSkillLevel;
	}
   
}
