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
	<s:text name="t2k.register.title" />
</div>

<div class="content_box">
	<s:text name="t2k.register.requiredmessage" />
</div>

<s:form action="Register">		
		<s:token name="registerToken" />
		<table class="formTable">
			
			<tr>
				<td><s:text name="t2k.register.emailaddressfield" /></td>
				<td><s:textfield name="registration.email" /></td>
			</tr>
			<tr>
				<td><s:text name="t2k.register.invitetokenfield" /></td>
				<td><s:textfield name="registration.inviteToken" /></td>
			</tr>
			<tr>
				<td><s:text name="t2k.register.fnamefield" /></td>
				<td><s:textfield name="registration.firstName" /></td>
			</tr>	
			<tr>
				<td><s:text name="t2k.register.lnamefield" /></td>
				<td><s:textfield name="registration.lastName" /></td>
			</tr>		
			<tr>
				<td><s:text name="t2k.register.useridfield" /></td>
				<td><s:textfield name="registration.userId" /></td>
			</tr>
			<tr>
				<td><s:text name="t2k.register.passwordfield" /></td>
				<td><s:password name="registration.password" /></td>
			</tr>
			<s:if test="hasFieldErrors()">
			<tr>
				<td colspan="2"><s:fielderror /></td>
			</tr>
			</s:if>
			<s:if test="hasActionMessages()">
			<tr>
				<td colspan="2"><s:actionmessage /></td>
			</tr>
			</s:if>
			<s:if test="hasActionErrors()">
			<tr>
				<td colspan="2"><s:actionerror /></td>
			</tr>
			</s:if>
		</table>
		
	<div>
		<s:submit value="%{getText('t2k.register.submitbutton')}" />
	</div>
</s:form>
