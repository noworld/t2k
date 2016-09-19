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
	protected List<NewSkill> skills;
	protected Map<String,Integer> nativeLanguages;
	protected int strength;
	protected int agility;
	protected int constitution;
	protected int intelligence;
	protected int education;
	protected int charisma;
	protected int bgSkill1;
	protected int bgSkill2;
	protected int bgSkill3;
	protected int bgSkill4;	
	protected List<Career> careers;
	
	public NewCharacter() {
		this.skills = new ArrayList<NewSkill>();
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

	public List<NewSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<NewSkill> skills) {
		this.skills = skills;
	}

	public Map<String, Integer> getNativeLanguages() {
		return nativeLanguages;
	}

	public void setNativeLanguages(Map<String, Integer> nativeLanguages) {
		this.nativeLanguages = nativeLanguages;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getBgSkill1() {
		return bgSkill1;
	}

	public void setBgSkill1(int bgSkill1) {
		this.bgSkill1 = bgSkill1;
	}

	public int getBgSkill2() {
		return bgSkill2;
	}

	public void setBgSkill2(int bgSkill2) {
		this.bgSkill2 = bgSkill2;
	}

	public int getBgSkill3() {
		return bgSkill3;
	}

	public void setBgSkill3(int bgSkill3) {
		this.bgSkill3 = bgSkill3;
	}

	public int getBgSkill4() {
		return bgSkill4;
	}

	public void setBgSkill4(int bgSkill4) {
		this.bgSkill4 = bgSkill4;
	}

	public List<Career> getCareers() {
		return careers;
	}

	public void setCareers(List<Career> careers) {
		this.careers = careers;
	}
	
}
