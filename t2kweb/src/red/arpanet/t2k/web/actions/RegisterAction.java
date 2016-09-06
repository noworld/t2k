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
	
	private static final String DB_ERROR_MSG = "t2k.register.dberror";
	private static final String NO_INVITE_ERROR_MSG = "t2k.register.noinviteerror";
	private static final String USER_ID_TAKEN_ERROR_MSG = "t2k.register.useridtakenerror";
	
	private static final Logger LOG = Logger.getLogger(RegisterAction.class);
	
	protected RegistrationInfo registration;
	protected String registerToken;

	public RegistrationInfo getRegistration() {
		return registration;
	}

	public void setRegistration(RegistrationInfo registration) {
		this.registration = registration;
	}

	public String getRegisterToken() {
		return registerToken;
	}

	public void setRegisterToken(String registerToken) {
		this.registerToken = registerToken;
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
					addActionError(getText(DB_ERROR_MSG));
				}
				
			} else {
				
				if(!activeInvite) {
					addActionMessage(getText(NO_INVITE_ERROR_MSG));
				}
				
				if(!availableUserName) {
					addActionMessage(getText(USER_ID_TAKEN_ERROR_MSG));
				}

			}

		}
		
		return INPUT;
	
	}

}

