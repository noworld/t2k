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

package red.arpanet.t2k.valueobjects;

public class NativeLanguage implements Comparable<NativeLanguage> {

	protected int id;
	protected String name;
	protected int skillId;
	protected String skillName;
	protected int targetNumber;	
	protected int enumValueId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getEnumValueId() {
		return enumValueId;
	}

	public void setEnumValueId(int enumValueId) {
		this.enumValueId = enumValueId;
	}

	@Override
	public int compareTo(NativeLanguage arg0) {
		return arg0.getTargetNumber() - this.getTargetNumber();
	}
	
}
