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
 * Entity implementation class for Entity: T2kSkilLevel
 *
 */
@Entity
@Table(name="t2k_skill_level")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kSkillLevel implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="level", nullable=false)
	private int level;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kSkill skill;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public T2kSkill getSkill() {
		return skill;
	}

	public void setSkill(T2kSkill skill) {
		this.skill = skill;
	}

}
