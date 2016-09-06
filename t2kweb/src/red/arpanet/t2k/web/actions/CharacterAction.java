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

import org.apache.log4j.Logger;

import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
public class CharacterAction extends BaseAction {
	
	private static final Logger LOG = Logger.getLogger(CharacterAction.class);

	private static final long serialVersionUID = 1L;
	
	public String execute() {
		
		return SUCCESS;
	
	}

}
