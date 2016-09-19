package red.arpanet.t2k.dao.model.career;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.dao.model.character.T2kNationality;
import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: Career
 *
 */
@Entity
@Table(name="t2k_career")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kCareer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;	
	
	@ManyToMany
	private List<T2kNationality> nationality;
	
	@ManyToOne
	private T2kEnumeratedValue careerGroup;
	
	@ManyToOne
	private T2kEnumeratedValue militaryBranch;
	
	@ManyToOne
	private T2kEnumeratedValue militaryOrg;
	
	@ManyToOne
	private T2kSkillPackage basicTrainingPackage;
	
	@ManyToOne
	private T2kSkillPackage firstTermPackage;
	
	@ManyToOne
	private T2kSkillPackage subsequentTermPackage;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}  
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T2kNationality> getNationality() {
		return nationality;
	}

	public void setNationality(List<T2kNationality> nationality) {
		this.nationality = nationality;
	}

	public T2kEnumeratedValue getCareerGroup() {
		return careerGroup;
	}

	public void setCareerGroup(T2kEnumeratedValue careerGroup) {
		this.careerGroup = careerGroup;
	}

	public T2kEnumeratedValue getMilitaryBranch() {
		return militaryBranch;
	}

	public void setMilitaryBranch(T2kEnumeratedValue militaryBranch) {
		this.militaryBranch = militaryBranch;
	}

	public T2kEnumeratedValue getMilitaryOrg() {
		return militaryOrg;
	}

	public void setMilitaryOrg(T2kEnumeratedValue militaryOrg) {
		this.militaryOrg = militaryOrg;
	}

	public T2kSkillPackage getBasicTrainingPackage() {
		return basicTrainingPackage;
	}

	public void setBasicTrainingPackage(T2kSkillPackage basicTrainingPackage) {
		this.basicTrainingPackage = basicTrainingPackage;
	}

	public T2kSkillPackage getFirstTermPackage() {
		return firstTermPackage;
	}

	public void setFirstTermPackage(T2kSkillPackage firstTermPackage) {
		this.firstTermPackage = firstTermPackage;
	}

	public T2kSkillPackage getSubsequentTermPackage() {
		return subsequentTermPackage;
	}

	public void setSubsequentTermPackage(T2kSkillPackage subsequentTermPackage) {
		this.subsequentTermPackage = subsequentTermPackage;
	}
   
}
