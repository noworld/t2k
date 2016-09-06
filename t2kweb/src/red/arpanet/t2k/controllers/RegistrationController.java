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

package red.arpanet.t2k.controllers;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;

import red.arpanet.t2k.dao.UserManager;
import red.arpanet.t2k.dao.model.T2kInvite;
import red.arpanet.t2k.dao.model.T2kUser;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.valueobjects.RegistrationInfo;
import red.arpanet.t2k.util.valueobjects.UserAccountInfo;

@CopyrightArpanet
public class RegistrationController {

	public static boolean isRegistrationComplete(RegistrationInfo registration) {

		boolean complete = false;
		
		complete = (registration != null
				&& StringUtils.isNotBlank(registration.getEmail())
				&& StringUtils.isNotBlank(registration.getFirstName())
				&& StringUtils.isNotBlank(registration.getLastName())
				&& StringUtils.isNotBlank(registration.getPassword())
				&& StringUtils.isNotBlank(registration.getUserId())
				&& StringUtils.isNotBlank(registration.getInviteToken()));
		
		return complete;
		
	}

	public static boolean checkForActiveInvite(RegistrationInfo registration) {
		
		registration.fix(); 
		
		T2kInvite invite = UserManager.getInviteForTokenAndEmail(registration.getInviteToken(), registration.getEmail());
				
		return (invite != null && invite.getRegistrationTimestamp() == null);
	}

	public static boolean checkForAvailableUserName(RegistrationInfo registration) {
		
		registration.fix();
		
		T2kUser user = UserManager.getUserById(registration.getUserId());
		
		return user == null;
	}

	public static boolean registerNewUser(RegistrationInfo registration) {
		
		registration.fix();
		
		return UserManager.registerNewUser(registration);
	}

	public static boolean sendInvite(String email) {

		email = email.trim().toLowerCase();
		
		T2kInvite invite = UserManager.createInviteWithNewToken(email);
		
		if(invite != null && SmtpController.sendInviteEmail(email)) {			
			return true;
		}
		
		return false;
	}
	
	public static boolean sendForgotPasswordEmail(String email) {

		email = email.trim().toLowerCase();
		
		T2kUser user = UserManager.updateUserWithToken(email);
		
		if(user != null && SmtpController.sendAccountRecoveryEmail(email, user.getRecoveryToken())) {			
			return true;
		}
		 
		return false;
	}
	
	public static UserAccountInfo getUserAccountInfo(String userId) {
		
		userId = userId.trim().toLowerCase();
		
		T2kUser user = UserManager.getUserById(userId);
		
		UserAccountInfo result = new UserAccountInfo();
		
		result.setEmail(user.getEmailAddress());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setUserId(userId);
		
		if(user.getLastLoginTimestamp() != null) {
			result.setLastLogin(Date.from(user.getLastLoginTimestamp().toInstant()));
		}
		
		if(user.getLastUpdateTimestamp() != null) {
			result.setLastUpdate(Date.from(user.getLastUpdateTimestamp().toInstant()));
		}
		
		result.fix();
		
		return result;
		
	}

	public static void updateLastLogin(String userId) {

		userId = userId.trim().toLowerCase();
		
		UserManager.updateLastLogin(userId);
		
	}
}
