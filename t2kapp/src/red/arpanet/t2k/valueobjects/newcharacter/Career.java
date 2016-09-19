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

import java.util.List;
import java.util.Map;

import red.arpanet.t2k.valueobjects.Contact;
import red.arpanet.t2k.valueobjects.Skill;

public class Career {

	protected int id;
	protected String desc;
	protected Map<String,String> entry;
	protected List<Skill> firstTermSkills;
	protected List<Skill> subsequentTermSkills;
	protected List<Contact> contacts;
	protected String special;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, String> getEntry() {
		return entry;
	}

	public void setEntry(Map<String, String> entry) {
		this.entry = entry;
	}

	public List<Skill> getFirstTermSkills() {
		return firstTermSkills;
	}

	public void setFirstTermSkills(List<Skill> firstTermSkills) {
		this.firstTermSkills = firstTermSkills;
	}

	public List<Skill> getSubsequentTermSkills() {
		return subsequentTermSkills;
	}

	public void setSubsequentTermSkills(List<Skill> subsequentTermSkills) {
		this.subsequentTermSkills = subsequentTermSkills;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
	
}
