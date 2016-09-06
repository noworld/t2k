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

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import red.arpanet.t2k.util.CopyrightArpanet;

import static red.arpanet.t2k.util.LogUtil.d;

@CopyrightArpanet
public class CharacterAction extends BaseAction {
	
	private static final Logger LOG = Logger.getLogger(CharacterAction.class);

	private static final long serialVersionUID = 1L;
	
	public String execute() {

		HttpSession httpSession = request.getSession(false);
		
		try {
			if(httpSession != null) {
				httpSession.invalidate(); 
				response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", "0");
			}
		} catch(Exception e) {
			d(LOG,String.format("Exception invalidating session: %s",e.getMessage()));
		}
		
		return LOGIN;
	
	}

}
