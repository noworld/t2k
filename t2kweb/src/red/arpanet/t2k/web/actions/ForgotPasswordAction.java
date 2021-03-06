/*
	�2016 ARPANET.RED
	
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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import red.arpanet.t2k.controllers.RegistrationController;
import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
public class ForgotPasswordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final String EMAIL_NOT_FOUND_MSG = "t2k.forgotpassword.emailnotfoundtext";
	
	private static final Logger LOG = Logger.getLogger(ForgotPasswordAction.class);
	
	protected String email;
	protected String token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String execute() {
		
		if(StringUtils.isNotBlank(email)) {
			if(RegistrationController.sendForgotPasswordEmail(email)) {
				i(LOG,String.format("Sent account recovery email to: %s", email));
				return SUCCESS;
			}
			
			addActionError(getText(EMAIL_NOT_FOUND_MSG));
		}
		
		return INPUT;
	
	}
}
