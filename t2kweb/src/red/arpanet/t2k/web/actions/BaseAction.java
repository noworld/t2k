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

package red.arpanet.t2k.web.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import red.arpanet.t2k.controllers.EnumeratedValueController;
import red.arpanet.t2k.util.CopyrightArpanet;

@CopyrightArpanet
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	
	public static final String LOGGED_IN = "LoggedIn";
	public static final String ACCOUNT_INFO = "AccountInfo";
	public static final Map<String,Integer> GROUP_IDS;
	
	protected Map<String,Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	static {
		Map<Integer,String> temp1 = EnumeratedValueController.getRootGroups();
		Map<String,Integer> temp2 = new HashMap<String,Integer>(temp1.size());
		
		for(Integer k : temp1.keySet()) {
			temp2.put(temp1.get(k).toLowerCase(), k);
		}
		
		GROUP_IDS = Collections.unmodifiableMap(temp2);
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	@SkipValidation
	public String show() {
		return SUCCESS; 
	}
	
	
}
