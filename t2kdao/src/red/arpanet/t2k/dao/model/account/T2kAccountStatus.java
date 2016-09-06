package red.arpanet.t2k.dao.model.account;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kAccountStatus
 *
 */
@Entity
@Table(name="t2k_account_status")
@NamedQueries({
	@NamedQuery(name="FindAccountStatusByName",query="select s from T2kAccountStatus s where s.name = :statusName")
})
@CopyrightArpanet
public class T2kAccountStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;	

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
   
}
