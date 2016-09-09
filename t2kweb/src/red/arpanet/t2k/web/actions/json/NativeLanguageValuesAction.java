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

import java.util.List;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.controllers.CharacterController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.valueobjects.newcharacter.NativeLanguage;
import red.arpanet.t2k.web.actions.BaseAction;

@CopyrightArpanet
@RequiresAuthentication
public class NativeLanguageValuesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected Integer nationalityId;
	protected List<NativeLanguage> languages;

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public List<NativeLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(List<NativeLanguage> languages) {
		this.languages = languages;
	}

	public String execute() {

		if(nationalityId != null) {
			languages = CharacterController.getDetailedNativeLanguagesByNationalityId(nationalityId);
			return SUCCESS;
		}
		
		return INPUT;

	}

}
