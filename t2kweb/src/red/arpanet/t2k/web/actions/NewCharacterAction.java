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

import static red.arpanet.t2k.util.LogUtil.t;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import red.arpanet.t2k.controllers.EnumeratedValueController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.valueobjects.newcharacter.NewCharacter;

@CopyrightArpanet
public class NewCharacterAction extends BaseAction {
	
	private static final Logger LOG = Logger.getLogger(NewCharacterAction.class);

	private static final long serialVersionUID = 1L;	
	
	protected static Map<Integer,String> genders = EnumeratedValueController.getGenders();
	protected static Map<Integer,String> factions = EnumeratedValueController.getPlayableFactions();
	
	protected String token;
	protected NewCharacter character;
	protected Map<Integer,String> nationalities;
	protected int selectedFaction = -1;
	protected int selectedNationality = -1;
	
	public NewCharacterAction() {
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

	public Map<Integer, String> getFactions() {
		return factions;
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

	public int getSelectedNationality() {
		return selectedNationality;
	}

	public void setSelectedNationality(int selectedNationality) {
		this.selectedNationality = selectedNationality;
	}

	@SkipValidation
	public String execute() {
		t(LOG,"In NewCharacterAction.execute()"); 
		
		if(session.get(NEW_CHARACTER) != null) {
			this.character = (NewCharacter)session.get(NEW_CHARACTER);
		} else {
			session.put(NEW_CHARACTER, this.character);
		}
		
		return SUCCESS;
	
	}

}
