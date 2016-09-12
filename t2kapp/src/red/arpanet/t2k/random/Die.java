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

package red.arpanet.t2k.random;

import java.security.SecureRandom;
import java.util.Random;

public class Die {

	private static Random RANDOM = new SecureRandom();
	
	private static int SIX = 6;
	private static int TEN = 10;
	private static int TWENTY = 20;
	
	public static int roll(int die) {
		return RANDOM.nextInt(die) + 1;
	}
	
	public static int rollSix() {
		return roll(SIX);
	}
	
	public static int rollTen() {
		return roll(TEN);
	}
	
	public static int rollTwenty() {
		return roll(TWENTY);
	}
	
}
