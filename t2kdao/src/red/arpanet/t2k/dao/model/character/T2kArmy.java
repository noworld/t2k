package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kArmy
 *
 */
@Entity
@Table(name="t2k_army")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kArmy implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;	
	
	@OneToMany(targetEntity=T2kNationality.class, fetch=FetchType.EAGER)
	private List<T2kNationality> nationalities;

	public T2kArmy() {
		super();
	}   
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
	
	public List<T2kNationality> getNationalities() {
		return nationalities;
	}
	
	public void setNationalities(List<T2kNationality> nationalities) {
		this.nationalities = nationalities;
	}
   
}
