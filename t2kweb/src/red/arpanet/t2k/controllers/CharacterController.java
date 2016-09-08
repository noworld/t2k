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

import red.arpanet.t2k.dao.CharacterManager;
import red.arpanet.t2k.dao.ValueManager;
import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.dao.model.character.T2kNationality;

public class CharacterController {
	
	private static final T2kEnumeratedValue NATO_ENUM_VALUE;
	private static final T2kEnumeratedValue PACT_ENUM_VALUE;
	
	static {
		NATO_ENUM_VALUE = ValueManager.findValueByEnumValue("NATO");
		PACT_ENUM_VALUE = ValueManager.findValueByEnumValue("Warsaw Pact");
	}

	public static Map<Integer,String> getNationalities() {
		Map<Integer,String> nationalities = null;
		
		List<T2kNationality> allNationalities = CharacterManager.getNationalities();
		
		nationalities = new HashMap<Integer,String>(allNationalities.size());
		
		for(T2kNationality n : allNationalities) {
			nationalities.put(n.getId(), n.getName());
		}
				
		return nationalities;
	}
	
	public static Map<Integer,String> getNationalitiesByFaction(T2kEnumeratedValue factionId) {
		Map<Integer,String> nationalities = null;
		
		List<T2kNationality> factionNationalities = CharacterManager.findNationalitiesByFaction(factionId);
		
		nationalities = new HashMap<Integer,String>(factionNationalities.size());
		
		for(T2kNationality n : factionNationalities) {
			nationalities.put(n.getId(), n.getName());
		}
				
		return nationalities;
	}
	
	public static Map<Integer,String> getNationalitiesByFactionId(int factionId) {
		
		T2kEnumeratedValue factionValue = ValueManager.findSingleValueById(factionId);
		
		Map<Integer,String> nationalities = null;
				
		return getNationalitiesByFaction(factionValue);
	}
	
	public static Map<Integer,String> getNatoNationalties() {
		return getNationalitiesByFaction(NATO_ENUM_VALUE);		
	}
	
	public static Map<Integer,String> getWarsawPactNationalties() {
		return getNationalitiesByFaction(PACT_ENUM_VALUE);
	}
}
