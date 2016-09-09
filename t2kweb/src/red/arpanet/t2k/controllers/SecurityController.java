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

package red.arpanet.t2k.controllers;

import java.util.Arrays;

import red.arpanet.t2k.dao.UserManager;
import red.arpanet.t2k.dao.model.account.T2kUser;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.HashUtil;
import red.arpanet.t2k.valueobjects.Credentials;

@CopyrightArpanet
public class SecurityController {

	public static boolean AuthenticateUser(Credentials credentials) {
		
		credentials.fix();
		
		T2kUser user = UserManager.getUserById(credentials.getUserId().toLowerCase());
		
		if(user == null) {
			return false;
		}
		
		byte[] hash = HashUtil.hashPassword(credentials.getPassword(), user.getSalt());
		
		return Arrays.equals(hash,user.getPassword());
	}
}
