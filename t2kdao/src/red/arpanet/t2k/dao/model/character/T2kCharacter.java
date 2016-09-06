package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.dao.model.account.T2kUser;
import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kCharacter
 *
 */
@Entity
@Table(name="t2k_character")
@NamedQueries({
	//@NamedQuery(name="FindInviteByTokenAndEmail",query="select i from T2kInvite i where i.emailAddress = :email and i.inviteToken = :token")
})
@CopyrightArpanet
public class T2kCharacter implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age;
	
	@Column(name="initiative")
	private int initiative;
	
	@Column(name="rank")
	private int rank;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="throw_range")
	private double throwRange;
	
	@Column(name="load")
	private double load;
	
	@Column(name="rads")
	private int rads;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kEnumeratedValue gender;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kUser player;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<T2kContact> contacts;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getThrowRange() {
		return throwRange;
	}

	public void setThrowRange(double throwRange) {
		this.throwRange = throwRange;
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}

	public int getRads() {
		return rads;
	}

	public void setRads(int rads) {
		this.rads = rads;
	}

	public T2kEnumeratedValue getGender() {
		return gender;
	}

	public void setGender(T2kEnumeratedValue gender) {
		this.gender = gender;
	}

	public T2kUser getPlayer() {
		return player;
	}

	public void setPlayer(T2kUser player) {
		this.player = player;
	}

	public List<T2kContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<T2kContact> contacts) {
		this.contacts = contacts;
	}
   
	
}
