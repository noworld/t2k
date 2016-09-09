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

package red.arpanet.t2k.util.valueobjects;

public class RegistrationInfo extends Credentials {

	protected String firstName;
	protected String lastName;
	protected String email;
	protected String inviteToken;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInviteToken() {
		return inviteToken;
	}

	public void setInviteToken(String inviteToken) {
		this.inviteToken = inviteToken;
	}
	
	public void fix() {
		this.email = this.email.trim().toLowerCase();
		this.userId = this.userId.trim().toLowerCase();
		this.firstName = this.firstName.trim();
		this.lastName = this.lastName.trim();
		this.inviteToken = this.inviteToken.trim();
	}
	
	
}
