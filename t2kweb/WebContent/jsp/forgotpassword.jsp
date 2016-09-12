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
	<s:text name="t2k.forgotpassword.title" />
</div>

<s:form action="ForgotPassword" method="post" validate="true">
	<s:token />
	<div>
		<table class="form_table">
			<tr>
				<td><s:text name="t2k.invite.emailaddressfield" /></td>
				<td><s:textfield name="email" /></td>
			</tr>
			<s:if test="hasFieldErrors()">
				<tr class="form_tableFooter">
					<td colspan="2"><s:fielderror/></td>					
				</tr>
			</s:if>
			<s:if test="hasActionErrors()">
			<tr>
				<td colspan="2"><s:actionerror /></td>
			</tr>
			</s:if>
		</table>
	</div>
		
	<div>
		<s:submit value="%{getText('t2k.forgotpassword.submitbutton')}" />
	</div>
</s:form>
<br />
<div class="content-box">
	<a href="<s:url action="ShowLogin" />"><s:text name="t2k.forgotpassword.loginlinktext" /></a>
</div>
