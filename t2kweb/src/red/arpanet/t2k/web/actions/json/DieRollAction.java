/*
	©2016 ARPANET.RED

	All rights reserved.

 * No Warranty * 
		1.1. "As-Is".
			The Software is provided "as is,"
			with all faults, defects and errors, and without
			warranty of any kind.

		1.2. No Liability.
			Licensor does not warrant that
			the Software will be free of bugs, errors, viruses
			or other defects, and Licensor shall have no
			liability of any kind for the use of or inability
			to use the software, the software content or any
			associated service.
 */


package red.arpanet.t2k.web.actions.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.random.Die;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.web.actions.BaseAction;

@CopyrightArpanet
@RequiresAuthentication
public class DieRollAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private int count;
	private int roll;
	private Integer[] dice;

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public Integer[] getDice() {
		return dice;
	}

	public void setDice(Integer[] dice) {
		this.dice = dice;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String execute() {
		
		return INPUT;

	}
	
	public String rollSix() {
		this.roll = Die.rollSix();
		this.dice = new Integer[]{this.roll};
		return SUCCESS;
	}
	
	public String rollTwoDSix() {
		int roll1 = Die.rollSix();
		int roll2 = Die.rollSix();
		this.dice = new Integer[]{roll1,roll2};
		this.roll = roll1 + roll2;
		return SUCCESS;
	}
	
	public String rollAttribute() {
		
		while(this.roll <= 0) {
			int roll1 = Die.rollSix();
			int roll2 = Die.rollSix();
			this.dice = new Integer[]{roll1,roll2};
			this.roll = roll1 + roll2;
			this.roll -= 2;
		}
		return SUCCESS;
	}
	
	public String rollTen() {
		this.roll = Die.rollTen();
		this.dice = new Integer[]{this.roll};
		return SUCCESS;
	}
	
	public String rollTwenty() {
		this.roll = Die.rollTwenty();
		this.dice = new Integer[]{this.roll};
		return SUCCESS;
	}
	
	public String rollBucket() {
				
		if(StringUtils.isNotBlank(type)
				&& count > 0) {
			
			List<Integer> rolls = new ArrayList<Integer>(count);
			int total = 0;
			
			for(int i = 0; i < count; i++) {
				int dieRoll = -1;
				
				switch(type.toLowerCase()) {
				case SIX: dieRoll = Die.rollSix();
					break;
				case TEN: dieRoll = Die.rollTen();
					break;
				case TWENTY: dieRoll = Die.rollTwenty();
					break;
				default:
					break;
				}
				
				total += dieRoll;
				rolls.add(dieRoll);				
			}
			
			this.roll = total;
			this.dice = rolls.toArray(new Integer[count]);
			
		} else {	
			this.roll = 0;
			this.dice = new Integer[]{};
		}
		return SUCCESS;
	}

}
