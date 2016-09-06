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
package red.arpanet.t2k.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: T2kInvite
 *
 */
@Entity
@Table(name="t2k_invite")
@NamedQueries({
	@NamedQuery(name="FindInviteByTokenAndEmail",query="select i from T2kInvite i where i.emailAddress = :email and i.inviteToken = :token")
})
@CopyrightArpanet
public class T2kInvite implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="invite_token", nullable=false)
	private String inviteToken;
	
	@Column(name="email_address", nullable=false)
	private String emailAddress;
	
	@Column(name="invite_timestamp", nullable=false)
	private Timestamp inviteTimestamp;
	
	@Column(name="registration_timestamp")
	private Timestamp registrationTimestamp;
  
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}  
	
	public String getInviteToken() {
		return this.inviteToken;
	}

	public void setInviteToken(String inviteToken) {
		this.inviteToken = inviteToken;
	}   
	
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Timestamp getInviteTimestamp() {
		return inviteTimestamp;
	}
	
	public void setInviteTimestamp(Timestamp inviteTimestamp) {
		this.inviteTimestamp = inviteTimestamp;
	}
	
	public Timestamp getRegistrationTimestamp() {
		return registrationTimestamp;
	}
	
	public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}   
}
