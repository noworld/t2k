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

package red.arpanet.sw.valueobjects.newcharacter;

import java.util.Date;
import java.util.Set;

import red.arpanet.sw.valueobjects.Attribute;
import red.arpanet.sw.valueobjects.Edge;
import red.arpanet.sw.valueobjects.Hindrance;
import red.arpanet.sw.valueobjects.Skill;

public class NewCharacter {

	protected int id;
	protected String name;
	protected String description;
	protected int gender;
	protected Set<Attribute> attributes;
	protected Set<Skill> skills;
	protected Set<Edge> edges;
	protected Set<Hindrance> hindrances;
	protected Date creationDate;
	protected Date updateDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}

	public Set<Hindrance> getHindrances() {
		return hindrances;
	}

	public void setHindrances(Set<Hindrance> hindrances) {
		this.hindrances = hindrances;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
