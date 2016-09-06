package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import javax.persistence.*;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kContact
 *
 */
@Entity
@Table(name="t2k_contact")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kContact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
