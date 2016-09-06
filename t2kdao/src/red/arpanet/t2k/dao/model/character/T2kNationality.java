package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kNationality
 *
 */
@Entity
@Table(name="t2k_nationality")
@NamedQueries({
	//@NamedQuery(name="FindInviteByTokenAndEmail",query="select i from T2kInvite i where i.emailAddress = :email and i.inviteToken = :token")
})
@CopyrightArpanet
public class T2kNationality implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;	
	
	@OneToMany
	private List<T2kArmy> armies;

	public T2kNationality() {
		super();
		this.armies = new ArrayList<T2kArmy>();
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

	public List<T2kArmy> getArmies() {
		return armies;
	}

	public void setArmies(List<T2kArmy> armies) {
		this.armies = armies;
	}
	
}
