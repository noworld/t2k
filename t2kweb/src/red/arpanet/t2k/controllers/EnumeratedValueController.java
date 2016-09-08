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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import red.arpanet.t2k.dao.ValueManager;
import red.arpanet.t2k.dao.model.T2kEnumeratedValue;

public class EnumeratedValueController {
	
	protected static final Map<String,Integer> GROUP_IDS;
	protected static final String GENDER_GROUP = "gender";
	protected static final String FACTIONS_GROUP = "faction";
	protected static final String PLAYABLE_FACTIONS = "warsaw pact nato";
	
	//TODO: Most of these can probably be cached...
	
	static {
		Map<Integer,String> temp1 = EnumeratedValueController.getRootGroups();
		Map<String,Integer> temp2 = new HashMap<String,Integer>(temp1.size());
		
		for(Integer k : temp1.keySet()) {
			temp2.put(temp1.get(k).toLowerCase(), k);
		}
		
		GROUP_IDS = Collections.unmodifiableMap(temp2);
	}

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
	
	public static Map<Integer,String> getGroupByName(String name) {
		return  getGroupById(GROUP_IDS.get(name.toLowerCase()));
	}
	
	public static Map<Integer,String> getGenders() {
		return getGroupById(GROUP_IDS.get(GENDER_GROUP));
	}
	
	public static Map<Integer,String> getPlayableFactions() {
		Map<Integer,String> temp = getGroupById(GROUP_IDS.get(FACTIONS_GROUP));
		Map<Integer,String> factions = new HashMap<Integer,String>(2);
		
		for(Integer k : temp.keySet()) {
			String currentFaction = temp.get(k).toLowerCase();
			
			if(PLAYABLE_FACTIONS.contains(currentFaction)) {
				factions.put(k, temp.get(k));
			}
		}
		
		return factions;
	}
}
