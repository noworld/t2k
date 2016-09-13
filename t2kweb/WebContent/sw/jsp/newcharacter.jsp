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

<br />
<div class="char_sheet">
		
	<s:form id="NewCharacterForm" action="sw/NewCharacter" method="post" validate="true">
		<s:token />

		<s:url id="charListUrl" action="Characters" />
		
		<div class="char_sheet_box">
			<div class="char_subtitle">
				<s:text name="sw.newchar.title" />
			</div>
			
		<div class="char_sheet_left">
			<table class="form_table">
				
				<tr>
					<td><s:text name="sw.newchar.namefield" /></td>
					<td><s:textfield name="character.name" /></td>
	
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
		</div>
		
		<div class="char_sheet_left">
			<table class="form_table">
				
				<tr>
					<td><s:text name="sw.newchar.descfield" /></td>
					<td><s:textfield name="character.description" /></td>
	
				</tr>

			</table>
		</div>
			
		</div>
		
		<div class="char_menu">
			<ul class="char_menubar">
				<li>
					<s:a href="#" id="NewCharacterSaveLink"><s:text name="t2k.char.savebutton" /></s:a>
				</li>
				<li>
					<s:a href="%{charListUrl}"><s:text name="t2k.char.cancelbutton" /></s:a>
				</li>
			</ul>
		</div>
	</s:form>
</div>