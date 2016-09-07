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


package red.arpanet.t2k.web.actions.json;

import java.util.Map;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.controllers.CharacterController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.web.actions.BaseAction;

@CopyrightArpanet
@RequiresAuthentication
public class CharacterValuesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	protected String groupName;
	protected Map<Integer,String> values;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Map<Integer, String> getValues() {
		return values;
	}

	public void setValues(Map<Integer, String> values) {
		this.values = values;
	}

	public String execute() {
		
		switch(groupName.toLowerCase()) {
		case "nationalities": values = CharacterController.getNationalities(); 
			break;
		}
		
		return SUCCESS;
	
	}
	
}
