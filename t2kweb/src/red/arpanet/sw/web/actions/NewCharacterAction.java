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


package red.arpanet.sw.web.actions;
       
import red.arpanet.sw.valueobjects.newcharacter.NewCharacter;
import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
@RequiresAuthentication
public class NewCharacterAction extends SwBaseAction {
	
	private static final long serialVersionUID = 1L;
	
	protected NewCharacter character;
		
	public NewCharacter getCharacter() {
		return character;
	}

	public void setCharacter(NewCharacter character) {
		this.character = character;
	}

	public String execute() {

		return SUCCESS;
	
	}

}
