package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kNationality
 *
 */
@Entity
@Table(name="t2k_nationality")
@NamedQueries({
	@NamedQuery(name="FindAllNationalities",query="select n from T2kNationality n")
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

	@ManyToOne(fetch=FetchType.EAGER)
	private T2kEnumeratedValue faction;
	
	@ManyToOne
	private T2kArmy army;
	
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

	public T2kEnumeratedValue getFaction() {
		return faction;
	}

	public void setFaction(T2kEnumeratedValue faction) {
		this.faction = faction;
	}

	public T2kArmy getArmy() {
		return army;
	}

	public void setArmy(T2kArmy army) {
		this.army = army;
	}

	
}
