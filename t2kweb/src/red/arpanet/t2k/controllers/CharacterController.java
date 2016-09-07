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
import red.arpanet.t2k.dao.model.character.T2kNationality;

public class CharacterController {

	public static Map<Integer,String> getNationalities() {
		Map<Integer,String> nationalities = null;
		
		List<T2kNationality> allNationalities = CharacterManager.getNationalities();
		
		nationalities = new HashMap<Integer,String>(allNationalities.size());
		
		for(T2kNationality n : allNationalities) {
			nationalities.put(n.getId(), n.getName());
		}
				
		return nationalities;
	}
	
}
