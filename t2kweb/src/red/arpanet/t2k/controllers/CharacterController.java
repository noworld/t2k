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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import red.arpanet.t2k.dao.CharacterManager;
import red.arpanet.t2k.dao.ValueManager;
import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.dao.model.character.T2kNationality;
import red.arpanet.t2k.dao.model.character.T2kNativeLanguage;
import red.arpanet.t2k.dao.model.character.T2kSkill;
import red.arpanet.t2k.valueobjects.NativeLanguage;

public class CharacterController {
	
	private static final T2kEnumeratedValue NATO_ENUM_VALUE;
	private static final T2kEnumeratedValue PACT_ENUM_VALUE;
	private static final List<String> BG_SKILL_NAMES;
	
	static {
		NATO_ENUM_VALUE = ValueManager.findValueByEnumValue("NATO");
		PACT_ENUM_VALUE = ValueManager.findValueByEnumValue("Warsaw Pact");
		
		List<String> temp = new ArrayList<String>();
		
		temp.add("Swimming");
		temp.add("Ground Vehicle (Wheeled)");
		temp.add("Computer");
		temp.add("Unarmed Martial Arts");
		temp.add("Riding");
		temp.add("Survival");
		temp.add("Small Watercraft");
		temp.add("Ground Vehicle (Motorcycle)");
		temp.add("Tracking");
		temp.add("Farming");
		
		BG_SKILL_NAMES = Collections.unmodifiableList(temp);
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
		return getNationalitiesByFaction(factionValue);
	}
	
	public static Map<Integer,String> getNatoNationalties() {
		return getNationalitiesByFaction(NATO_ENUM_VALUE);		
	}
	
	public static Map<Integer,String> getWarsawPactNationalties() {
		return getNationalitiesByFaction(PACT_ENUM_VALUE);
	}
	
	public static Map<Integer,String> getBackgroundSkills() {
		
		Map<Integer,String> bgSkills = null;
		List<T2kSkill> skillsList = CharacterManager.findSkillsByNameList(BG_SKILL_NAMES);

		if(skillsList != null) {

			bgSkills = new HashMap<Integer,String>(skillsList.size());

			for(T2kSkill s : skillsList) {
				bgSkills.put(s.getId(), s.getName());
			}
		}

		return bgSkills;
	}
	
	public static Map<Integer,String> getNativeLanguagesByNationalityId(int id) {
		Map<Integer,String> languages = null;
		
		Set<T2kNativeLanguage> natLangs = CharacterManager.findLanguagesByNationalityId(id);
		
		if(natLangs != null) {
			languages = new HashMap<Integer,String>(natLangs.size());
			for(T2kNativeLanguage l : natLangs) {
				languages.put(l.getId(), l.getLanguageSkill().getName());
			}
		}
		
		return languages;
	}
	
	public static List<NativeLanguage> getDetailedNativeLanguagesByNationalityId(int id) {
		List<NativeLanguage> languages = null;
		
		Set<T2kNativeLanguage> natLangs = CharacterManager.findLanguagesByNationalityId(id);
		
		if(natLangs != null) {
			languages = new ArrayList<NativeLanguage>(natLangs.size());
			
			for(T2kNativeLanguage l : natLangs) {
				
				NativeLanguage nl = new NativeLanguage();
				
				nl.setId(l.getId());
				nl.setTargetNumber(l.getTargetNumber());
				nl.setName(l.getLanguageSkill().getAssociatedValue().getEnumValue());
				nl.setSkillId(l.getLanguageSkill().getId());
				nl.setSkillName(l.getLanguageSkill().getName());
				nl.setEnumValueId(l.getLanguageSkill().getAssociatedValue().getId());
				
				languages.add(nl);
			}
		}
		
		Collections.sort(languages);
		
		return languages;
	}
}
