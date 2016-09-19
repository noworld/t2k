package red.arpanet.t2k.dao.model.career;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kSkillPackage
 *
 */
@Entity
@Table(name="t2k_skill_package")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kSkillPackage implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;	
	
	@ManyToMany
	private List<T2kSkillOption> skillOptions;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T2kSkillOption> getSkillOptions() {
		return skillOptions;
	}

	public void setSkillOptions(List<T2kSkillOption> skillOptions) {
		this.skillOptions = skillOptions;
	}
   
}
