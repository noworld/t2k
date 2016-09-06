package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import javax.persistence.*;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kRank
 *
 */
@Entity
@Table(name="t2k_rank")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kRank implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="level", nullable=false)
	private int level;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kNationality nationality;

	public int getId() {
		return id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T2kNationality getNationality() {
		return nationality;
	}

	public void setNationality(T2kNationality nationality) {
		this.nationality = nationality;
	}
   
}
