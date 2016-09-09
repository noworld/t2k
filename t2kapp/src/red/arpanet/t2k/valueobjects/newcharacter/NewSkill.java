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

package red.arpanet.t2k.valueobjects.newcharacter;

import red.arpanet.t2k.valueobjects.Skill;

public class NewSkill extends Skill {

	protected boolean retained = false;

	public boolean isRetained() {
		return retained;
	}

	public void setRetained(boolean retained) {
		this.retained = retained;
	}
	
	
}
