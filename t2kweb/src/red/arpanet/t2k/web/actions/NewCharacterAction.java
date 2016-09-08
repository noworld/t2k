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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import red.arpanet.t2k.controllers.CharacterController;
import red.arpanet.t2k.controllers.EnumeratedValueController;
import red.arpanet.t2k.dao.CharacterManager;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.valueobjects.NewCharacter;

@CopyrightArpanet
public class NewCharacterAction extends BaseAction {
	
	private static final Logger LOG = Logger.getLogger(NewCharacterAction.class);

	private static final long serialVersionUID = 1L;	
	
	protected String token;
	protected NewCharacter character;
	protected Map<Integer,String> genders;
	protected Map<Integer,String> nationalities;
	protected Map<Integer,String> factions;
	protected int selectedFaction = -1;
	
	public NewCharacterAction() {
		this.genders = EnumeratedValueController.getGenders();
		this.factions = EnumeratedValueController.getPlayableFactions();
		this.nationalities =  new HashMap<Integer,String>();
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public NewCharacter getCharacter() {
		return character;
	}

	public void setCharacter(NewCharacter character) {
		this.character = character;
	}

	public Map<Integer, String> getGenders() {
		return genders;
	}

	public void setGenders(Map<Integer, String> genders) {
		this.genders = genders;
	}

	public Map<Integer, String> getFactions() {
		return factions;
	}

	public void setFactions(Map<Integer, String> factions) {
		this.factions = factions;
	}

	public Map<Integer, String> getNationalities() {
		return nationalities;
	}

	public void setNationalities(Map<Integer, String> nationalities) {
		this.nationalities = nationalities;
	}

	public int getSelectedFaction() {
		return selectedFaction;
	}

	public void setSelectedFaction(int selectedFaction) {
		this.selectedFaction = selectedFaction;
	}

	public String execute() {
		
		if(selectedFaction >= 0) {
			this.nationalities = CharacterController.getNationalitiesByFaction(selectedFaction);
		}
		
		return SUCCESS;
	
	}

}
