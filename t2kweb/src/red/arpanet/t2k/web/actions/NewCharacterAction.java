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
	protected Integer selectedNationality;
	protected String languagesFinished;
	protected Map<String,Integer> selectedLanguages;
	protected Map<Integer,String> nationalities;
	
	public NewCharacterAction() {
		this.selectedLanguages =  new HashMap<String,Integer>();
		this.nationalities = new HashMap<Integer,String>();
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

	public Integer getSelectedNationality() {
		return selectedNationality;
	}

	public void setSelectedNationality(Integer selectedNationality) {
		this.selectedNationality = selectedNationality;
	}

	public Map<Integer, String> getGenders() {
		return genders;
	}

	public Map<Integer, String> getFactions() {
		return factions;
	}

	public Map<String, Integer> getSelectedLanguages() {
		return selectedLanguages;
	}

	public void setSelectedLanguages(Map<String, Integer> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

	public String getLanguagesFinished() {
		return languagesFinished;
	}

	public void setLanguagesFinished(String languagesFinished) {
		this.languagesFinished = languagesFinished;
	}

	public Map<Integer, String> getNationalities() {
		return nationalities;
	}

	public void setNationalities(Map<Integer, String> nationalities) {
		this.nationalities = nationalities;
	}

	@SkipValidation
	public String execute() {
		t(LOG,"In NewCharacterAction.execute()"); 
		
		if(character != null) {
			this.selectedNationality = character.getNationality();
			
			if(languagesFinished.toLowerCase().equals(Boolean.TRUE.toString().toLowerCase())) {
				this.selectedLanguages = character.getNativeLanguages();
			}
		}
		
		return SUCCESS;
	
	}

}
