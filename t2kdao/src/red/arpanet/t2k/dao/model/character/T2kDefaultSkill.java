package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="t2k_default_skill")
@NamedQueries({
//	@NamedQuery(name="FindAllNationalities",query="select n from T2kNationality n"),
//	@NamedQuery(name="FindNationalitiesByFaction",query="select n from T2kNationality n where n.faction = :faction")
})
@CopyrightArpanet
public class T2kDefaultSkill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="level")
	private int level;
	
	@ManyToOne
	private T2kNationality nationality;
	
	@ManyToOne
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

	public T2kNationality getNationality() {
		return nationality;
	}

	public void setNationality(T2kNationality nationality) {
		this.nationality = nationality;
	}

	public T2kSkill getSkill() {
		return skill;
	}

	public void setSkill(T2kSkill skill) {
		this.skill = skill;
	}
   
}
