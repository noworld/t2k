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

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.controllers.EnumeratedValueController;
import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
@RequiresAuthentication
public class SysAdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Integer selectedGroup = null;
	private Integer selectedSubGroup = null;
	private Integer selectedValue = null;
	private Map<Integer,String> groups;
	private Map<Integer,String> subGroups;	
	private Map<Integer,String> values;

	public Integer getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Integer selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public Integer getSelectedSubGroup() {
		return selectedSubGroup;
	}

	public void setSelectedSubGroup(Integer selectedSubGroup) {
		this.selectedSubGroup = selectedSubGroup;
	}

	public Integer getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(Integer selectedValue) {
		this.selectedValue = selectedValue;
	}

	public Map<Integer, String> getGroups() {
		return groups;
	}

	public void setGroups(Map<Integer, String> groups) {
		this.groups = groups;
	}

	public Map<Integer, String> getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(Map<Integer, String> subGroups) {
		this.subGroups = subGroups;
	}

	public Map<Integer, String> getValues() {
		return values;
	}

	public void setValues(Map<Integer, String> values) {
		this.values = values;
	}

	public String execute() {
		if(groups == null) {
			groups = EnumeratedValueController.getRootGroups();
		}
		
		if(selectedGroup != null) {
			subGroups = EnumeratedValueController.getGroupById(selectedGroup);
		} else {
			subGroups = new HashMap<Integer,String>();
		}
		
		if(selectedSubGroup != null) {
			values = EnumeratedValueController.getGroupById(selectedSubGroup);
		} else {
			values = new HashMap<Integer,String>();
		}
		
		return SUCCESS;
	
	}

}

