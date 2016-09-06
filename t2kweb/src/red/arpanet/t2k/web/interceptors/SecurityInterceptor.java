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

package red.arpanet.t2k.web.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import red.arpanet.t2k.annotations.RequiresAuthentication;
import red.arpanet.t2k.web.actions.BaseAction;

public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//Check to see if the action is protected
		//by the RequiresAuthenticacion annotation
		boolean annotated = invocation.getAction().getClass().isAnnotationPresent(RequiresAuthentication.class);
		
		//If not annotated then just let them through
		if(!annotated) {
			return invocation.invoke();
		}
		
		//Assume not authenticated
		boolean authenticated = false;
		
		//Check session to see if they are authenticated
		Object authProp = invocation.getInvocationContext().getSession().get(BaseAction.LOGGED_IN);		
		if(authProp != null) {
			authenticated = Boolean.valueOf(authProp.toString());
		}

		//If the user is not authenticated
		//then force a login
		if(!authenticated) {
			return BaseAction.LOGIN;
		}
		
		//Otherwise, continue on		
		return invocation.invoke();
	}

}	