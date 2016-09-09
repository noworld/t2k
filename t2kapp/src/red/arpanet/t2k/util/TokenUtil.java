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

import static red.arpanet.t2k.util.LogUtil.e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class TokenUtil {
	
	private static final Logger LOG = Logger.getLogger(TokenUtil.class);
	
	private static final String TOKENS_FILE = "tokens.txt";
	private static final int MIN_TOKEN_LENGTH = 6;	
	private static final List<String> TOKENS;
	private static Random RANDOM = new Random();
	
	static {
		List<String> tempTokens = new ArrayList<String>();
		
		InputStream in = TokenUtil.class.getClassLoader().getResourceAsStream(TOKENS_FILE);		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		
		try {
			while((line = reader.readLine()) != null) {
				if(line.trim().length() >= MIN_TOKEN_LENGTH) {
					tempTokens.add(line.trim().toLowerCase());
				}
			}
		} catch (IOException e) {
			e(LOG,"Exception occurred initializing TokenUtil class.",e);
		}
		
		TOKENS = Collections.unmodifiableList(tempTokens);

	}	

	public static String createToken() {

		return TOKENS.get(RANDOM.nextInt(TOKENS.size()));
		
	}
}
