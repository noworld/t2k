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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Action;

import red.arpanet.t2k.controllers.RegistrationController;
import red.arpanet.t2k.controllers.SecurityController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.valueobjects.Credentials;
import red.arpanet.t2k.util.valueobjects.UserAccountInfo;

@CopyrightArpanet
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LoginAction.class);
	
	protected Credentials credentials = new Credentials();

	public Credentials getCredentials() {
		return credentials;
	}
	
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String execute() {
		if(credentials != null && StringUtils.isNotBlank(credentials.getUserId())) {
			i(LOG, String.format("User attempting login: %s", credentials.getUserId()));
			if(SecurityController.AuthenticateUser(credentials)) {

				RegistrationController.updateLastLogin(credentials.getUserId());
				
				UserAccountInfo accountInfo = RegistrationController.getUserAccountInfo(credentials.getUserId());
				
				session.put(LOGGED_IN, true);
				session.put(ACCOUNT_INFO,accountInfo);
				
				return SUCCESS;
			}
			
			addActionError("Invalid User ID/Password combination.");
		}
		
		return Action.INPUT;
	
	}	

}
