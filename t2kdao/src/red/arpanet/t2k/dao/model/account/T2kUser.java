/*
	©2016 ARPANET.RED
	
	All rights reserved.
	
	* No Warranty * 
		1.1. "As-Is".
			The Software is provided "as is,"
			with all faults, defects and errors, and without
			warranty of any kind.
			
		1.2. No Liability.
			Licensor does not warrant that
			the Software will be free of bugs, errors, viruses
			or other defects, and Licensor shall have no
			liability of any kind for the use of or inability
			to use the software, the software content or any
			associated service.
*/

package red.arpanet.t2k.dao.model.account;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.Unique;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="t2k_user")
@NamedQueries({
	@NamedQuery(name="FindUserByUserId",query="select u from T2kUser u where u.userId = :userId",
			hints={@QueryHint(name="openjpa.hint.OptimizeResultCount", value="1")}),
	@NamedQuery(name="FindUserByEmailAddress",query="select u from T2kUser u where u.emailAddress = :email",
			hints={@QueryHint(name="openjpa.hint.OptimizeResultCount", value="1")})
})
@CopyrightArpanet
public class T2kUser implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Unique
	@Column(name="user_id", nullable=false)
	private String userId;
	
	@Lob
	@Column(name="salt", nullable=false, length=16)
	private byte[] salt;
	
	@Lob
	@Column(name="password", nullable=false, length=64)
	private byte[] password;
	
	@Column(name="email_address", nullable=false, unique=true)
	private String emailAddress;
	
	@Column(name="recovery_token")
	private String recoveryToken;
	
	@Column(name="account_status", nullable=false)
	private T2kAccountStatus accountStatus;
	
	@Column(name="last_login_timestamp")
	private Timestamp lastLoginTimestamp;
	
	@Column(name="last_update_timestamp")
	private Timestamp lastUpdateTimestamp;
	
	@Column(name="created_timestamp", nullable=false)
	private Timestamp createdTimestamp;
	
	@OneToMany
	private List<T2kRole> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRecoveryToken() {
		return recoveryToken;
	}

	public void setRecoveryToken(String recoveryToken) {
		this.recoveryToken = recoveryToken;
	}

	public T2kAccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(T2kAccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Timestamp getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public Timestamp getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public List<T2kRole> getRoles() {
		return roles;
	}

	public void setRoles(List<T2kRole> roles) {
		this.roles = roles;
	}
   
}
