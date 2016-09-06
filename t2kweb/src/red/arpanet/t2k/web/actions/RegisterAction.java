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


package red.arpanet.t2k.web.actions;

import static red.arpanet.t2k.util.LogUtil.i;

import org.apache.log4j.Logger;

import red.arpanet.t2k.controllers.RegistrationController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.valueobjects.RegistrationInfo;

@CopyrightArpanet
public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(RegisterAction.class);
	
	protected RegistrationInfo registration;
	protected boolean completeError = false;
	protected boolean inviteError = false;
	protected boolean userNameError = false;
	protected boolean databaseError = false;

	public RegistrationInfo getRegistration() {
		return registration;
	}

	public void setRegistration(RegistrationInfo registration) {
		this.registration = registration;
	}

	public boolean isCompleteError() {
		return completeError;
	}

	public void setCompleteError(boolean completeError) {
		this.completeError = completeError;
	}

	public boolean isInviteError() {
		return inviteError;
	}

	public void setInviteError(boolean inviteError) {
		this.inviteError = inviteError;
	}

	public boolean isUserNameError() {
		return userNameError;
	}

	public void setUserNameError(boolean userNameError) {
		this.userNameError = userNameError;
	}

	public boolean isDatabaseError() {
		return databaseError;
	}

	public void setDatabaseError(boolean databaseError) {
		this.databaseError = databaseError;
	}

	public String execute() {
		if(RegistrationController.isRegistrationComplete(registration)) {
			i(LOG, String.format("User attempting registration: %s/%s", registration.getUserId(), registration.getEmail()));
			
			boolean activeInvite = RegistrationController.checkForActiveInvite(registration);			
			boolean availableUserName = RegistrationController.checkForAvailableUserName(registration);
			
			if(activeInvite && availableUserName) {
				
				if(RegistrationController.registerNewUser(registration)) {
					return SUCCESS;
				} else {
					databaseError = true;
				}
				
			} else {
				inviteError = !activeInvite;
				userNameError = !availableUserName;
			}

		} else {
			completeError = true;
		}
		
		return INPUT;
	
	}

}
