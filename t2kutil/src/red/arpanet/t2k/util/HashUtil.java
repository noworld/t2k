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

package red.arpanet.t2k.util;

import static red.arpanet.t2k.util.LogUtil.e;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

@CopyrightArpanet
public class HashUtil {

	private static final Logger LOG = Logger.getLogger(HashUtil.class);

	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 5;
	private static final int KEY_LEN = 512;
	private static final int SALT_LEN = 16;
	private static final String KEY_FACTORY = "PBKDF2WithHmacSHA512";

	public static byte[] hashPassword(String password, byte[] salt) {
		byte[] hash = null;

		SecretKeyFactory skf = null;
		SecretKey key = null;

		try {
			skf = SecretKeyFactory.getInstance(KEY_FACTORY);
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LEN);
			key = skf.generateSecret(spec);
		} catch (NoSuchAlgorithmException e) {
			e(LOG, "Exception hashing password.", e);
		} catch (InvalidKeySpecException e) {
			e(LOG, "Exception hashing password.", e);
		}         

		hash = key.getEncoded();

		return hash;
	}
	
	public static byte[] generateSalt() {
		byte[] salt = new byte[SALT_LEN];
	    RANDOM.nextBytes(salt);
	    return salt;
	}
}
