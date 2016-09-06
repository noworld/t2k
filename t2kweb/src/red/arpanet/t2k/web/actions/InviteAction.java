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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.controllers.RegistrationController;
import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
@RequiresAuthentication
public class InviteAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(InviteAction.class);
	
	protected String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String execute() {
		
		if(StringUtils.isNotBlank(email)) {
			if(RegistrationController.sendInvite(email)) {
				i(LOG,String.format("Sent registration invite to: %s", email));
				return SUCCESS;
			}
		}
		
		return INPUT;
	
	}
}
