<!--
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
-->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div class="subtitle" >
	<s:text name="t2k.login.title" />
</div>

<s:form action="Login" method="post" validate="true">

		<table class="formTable">
			<tr>
				<td><s:text name="t2k.login.useridfield" /></td>
				<td><s:textfield name="credentials.userId" /></td>
			</tr>
			<tr>
				<td><s:text name="t2k.login.passwordfield" /></td>
				<td><s:password name="credentials.password" /></td>
			</tr>	
			<s:if test="hasFieldErrors()">
				<tr class="formTableFooter">
					<td colspan="2"><s:fielderror/></td>					
				</tr>
			</s:if>
			<s:if test="hasActionErrors()">				
				<tr class="formTableFooter">
					<td colspan="2"><s:actionerror/></td>					
				</tr>
			</s:if>
		</table>
		
	<div>
		<s:submit value="%{getText('t2k.login.submitbutton')}" />
	</div>
</s:form>

<div class="content_box">
	<s:text name="t2k.login.registertext1" />
	<a href="<s:url action="Register" />"><s:text name="t2k.login.registertext2" /></a>
	<s:text name="t2k.login.registertext3" />
</div>