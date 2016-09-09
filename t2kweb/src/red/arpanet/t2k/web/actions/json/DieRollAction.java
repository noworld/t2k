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

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.random.Die;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.web.actions.BaseAction;

@CopyrightArpanet
@RequiresAuthentication
public class DieRollAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private int roll;

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String execute() {
		
		return INPUT;

	}
	
	public String rollSix() {
		this.roll = Die.rollSix();
		return SUCCESS;
	}
	
	public String rollTwoDSix() {
		this.roll = Die.rollTwoDSix();
		return SUCCESS;
	}
	
	public String rollTen() {
		this.roll = Die.rollTen();
		return SUCCESS;
	}
	
	public String rollTwenty() {
		this.roll = Die.rollTwenty();
		return SUCCESS;
	}

}
