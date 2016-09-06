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

import org.apache.log4j.Logger;

@CopyrightArpanet
public class LogUtil {

	public static void t(Logger l, String message) {
		if(l.isTraceEnabled()) {
			l.trace(message);
		}
	}
	
	public static void d(Logger l, String message) {
		if(l.isDebugEnabled()) {
			l.debug(message);
		}
	}
	
	public static void i(Logger l, String message) {
		if(l.isInfoEnabled()) {
			l.info(message);
		}
	}
	
	public static void w(Logger l, String message) {
		l.warn(message);
	}

	public static void w(Logger l, String message, Throwable t) {
		l.warn(message, t);
	}
	
	public static void e(Logger l, String message) {
		l.error(message);
	}

	public static void e(Logger l, String message, Throwable t) {
		l.error(message, t);
	}
	
	public static void f(Logger l, String message) {
		l.fatal(message);
	}

	public static void f(Logger l, String message, Throwable t) {
		l.fatal(message, t);
	}

}
