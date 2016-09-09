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

package red.arpanet.t2k.valueobjects.newcharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import red.arpanet.t2k.valueobjects.Skill;

public class NewCharacter {

	protected String name;
	protected int gender;
	protected int nationality;
	protected int faction;
	protected List<Skill> skills;
	protected Map<String,Integer> nativeLanguages;
	
	
	public NewCharacter() {
		this.skills = new ArrayList<Skill>();
		this.nativeLanguages = new HashMap<String,Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getNationality() {
		return nationality;
	}

	public void setNationality(int nationality) {
		this.nationality = nationality;
	}

	public int getFaction() {
		return faction;
	}

	public void setFaction(int faction) {
		this.faction = faction;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Map<String, Integer> getNativeLanguages() {
		return nativeLanguages;
	}

	public void setNativeLanguages(Map<String, Integer> nativeLanguages) {
		this.nativeLanguages = nativeLanguages;
	}
	
}
