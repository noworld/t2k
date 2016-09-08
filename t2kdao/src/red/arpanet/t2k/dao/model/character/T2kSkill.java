/*
	©2016 ARPANET.RED
	
	All rights reserved.
	
	* No Warranty * 
		1.1. "As-Is".
			The Software is provided "as is,"
			with all faults, defects and errors, and without
			warranty of any kind.
			
		1.1. No Liability.
			Licensor does not warrant that
			the Software will be free of bugs, errors, viruses
			or other defects, and Licensor shall have no
			liability of any kind for the use of or inability
			to use the software, the software content or any
			associated service.
*/
package red.arpanet.t2k.dao.model.character;

import java.io.Serializable;
import javax.persistence.*;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kRole
 *
 */
@Entity
@Table(name="t2k_skill")
@NamedQueries({
	//@NamedQuery(name="FindRoleByName",query="select r from T2kRole r where r.name = :roleName")
})
@CopyrightArpanet
public class T2kSkill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private T2kAttribute controllingAttribute;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private T2kEnumeratedValue skillGroup;
	
	@ManyToOne
	private T2kEnumeratedValue associatedValue;
	
	public int getId() {
		return id;
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

	public T2kAttribute getControllingAttribute() {
		return controllingAttribute;
	}

	public void setControllingAttribute(T2kAttribute controllingAttribute) {
		this.controllingAttribute = controllingAttribute;
	}

	public T2kEnumeratedValue getSkillGroup() {
		return skillGroup;
	}

	public void setSkillGroup(T2kEnumeratedValue skillGroup) {
		this.skillGroup = skillGroup;
	}

	public T2kEnumeratedValue getAssociatedValue() {
		return associatedValue;
	}

	public void setAssociatedValue(T2kEnumeratedValue associatedValue) {
		this.associatedValue = associatedValue;
	}
	
}
