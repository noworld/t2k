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

package red.arpanet.t2k.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TokenUtil {
	
	private static final int MIN_TOKEN_LENGTH = 6;	
	private static final List<String> TOKENS;
	private static volatile int readPos = 0;
	
	static {
		List<String> tempTokens = new ArrayList<String>();
		
		InputStream in = TokenUtil.class.getClassLoader().getResourceAsStream("words2.txt");		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		
		try {
			while((line = reader.readLine()) != null) {
				if(line.trim().length() >= MIN_TOKEN_LENGTH) {
					tempTokens.add(line.trim().toLowerCase());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TOKENS = Collections.unmodifiableList(tempTokens);
		
		Random random = new Random();
		readPos = random.nextInt(TOKENS.size());
	}	

	public static String createToken() {
		
		if(readPos >= TOKENS.size() - 1) {
			readPos = 0;
		}
		
		return TOKENS.get(readPos++);
		
	}
}
