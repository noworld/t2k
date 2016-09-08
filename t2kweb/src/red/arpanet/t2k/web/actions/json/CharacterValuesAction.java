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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.controllers.CharacterController;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.web.actions.BaseAction;

@CopyrightArpanet
@RequiresAuthentication
public class CharacterValuesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final Map<Integer,String> PARAMS;
	private static final String PARAMS_NAME = "params";
	private static final String NATIONALITIES_PARAM = "nationalities";
	private static final String NATO_NAT_PARAM = "nato_nationalities";
	private static final String PACT_NAT_PARAM = "pact_nationalities";
	private static final String NAT_BY_ID_PARAM = "nationalities_by_id";
	private static final String BG_SKILLS_PARAM = "background_skills";
	private static final String NAT_LANGUAGES_PARAM = "native_languages";
	
	static {
		Map<Integer,String> temp = new HashMap<Integer,String>(6);
		
		temp.put(0, NATIONALITIES_PARAM);
		temp.put(1, NATO_NAT_PARAM);
		temp.put(2, PACT_NAT_PARAM);
		temp.put(3, NAT_BY_ID_PARAM);
		temp.put(4, BG_SKILLS_PARAM);
		temp.put(5, NAT_LANGUAGES_PARAM);
		
		PARAMS = Collections.unmodifiableMap(temp);
	}

	protected String groupName;
	protected Integer groupVal;
	protected Map<Integer,String> values;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupVal() {
		return groupVal;
	}

	public void setGroupVal(Integer groupVal) {
		this.groupVal = groupVal;
	}

	public Map<Integer, String> getValues() {
		return values;
	}

	public void setValues(Map<Integer, String> values) {
		this.values = values;
	}

	public String execute() {

		if(StringUtils.isNotBlank(groupName)) {
			switch(groupName.toLowerCase()) {
			case NATIONALITIES_PARAM: values = CharacterController.getNationalities(); 
			break;
			case NATO_NAT_PARAM: values = CharacterController.getNatoNationalties();
			break;
			case PACT_NAT_PARAM: values = CharacterController.getWarsawPactNationalties(); 
			break;
			case NAT_BY_ID_PARAM: values = CharacterController.getNationalitiesByFactionId(groupVal);
			break;
			case BG_SKILLS_PARAM: values = CharacterController.getBackgroundSkills();
			break;
			case NAT_LANGUAGES_PARAM: values = CharacterController.getNativeLanguagesByNationalityId(groupVal);
			default:
			break;
			};
			
			return SUCCESS;
		}

		groupName = PARAMS_NAME;
		values = PARAMS;
		
		return INPUT;

	}

}
