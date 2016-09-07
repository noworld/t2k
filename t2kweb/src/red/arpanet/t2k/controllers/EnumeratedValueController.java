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

package red.arpanet.t2k.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import red.arpanet.t2k.dao.ValueManager;
import red.arpanet.t2k.dao.model.T2kEnumeratedValue;

public class EnumeratedValueController {

	public static Map<Integer,String> getRootGroups() {
		
		List<T2kEnumeratedValue> values = ValueManager.findRootGroups();
		Map<Integer,String> groups = new HashMap<Integer,String>();
		
		for(T2kEnumeratedValue v : values) {
			groups.put(v.getId(), v.getEnumValue());
		}
		
		return groups;
	}
	
	public static Map<Integer,String> getGroupById(int id) {
		
		List<T2kEnumeratedValue> values = ValueManager.findGroupById(id);
		Map<Integer,String> groups = new HashMap<Integer,String>();
		
		for(T2kEnumeratedValue v : values) {
			groups.put(v.getId(), v.getEnumValue());
		}
		
		return groups;
	}
}
