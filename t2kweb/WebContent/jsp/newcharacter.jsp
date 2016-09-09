<!--
	�2016 ARPANET.RED
	
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
		
	<s:form id="NewCharacterForm" action="NewCharacter" method="post" validate="true">
		<s:token />

		<s:url id="charListUrl" action="Characters" />
		
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
		
		<div class="char_sheet_box">
			<div class="char_subtitle">
				<s:text name="t2k.newchar.title" />
			</div>
			
			<s:hidden name="selectedNationality" />
			<s:hidden name="languagesFinished" />
			<s:iterator value="selectedLanguages">
				<s:hidden name="selectedLanguages['%{key}']" value="%{value}" />
			</s:iterator>
			
			<table class="formTable">
				
				<tr>
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="character.name" /></td>
					
					<td><s:text name="t2k.newchar.skillstext" /></td>
					<td> 
						<s:iterator value="character.skills" status="stat" var="skill">
              				<s:textfield value="%{#skill.name}" name="character.skills[%{#stat.count}].name"/>
              				<br/>
              			</s:iterator>
              		</td>
					
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="def" /></td>
				</tr>
				
				<tr>
					<td><s:text name="t2k.newchar.genderfield" /></td>
					<td><s:select
							name="character.gender"
							list="genders"/></td>
							
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="abc" /></td>
					
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="def" /></td>
				</tr>
				
				<tr>
					<td><s:text name="t2k.newchar.factionfield" /></td>
					<td><s:select
							name="character.faction"
							list="factions"/></td>
							
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="abc" /></td>
					
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="def" /></td>
				</tr>	
				
				<tr>
					<td><s:text name="t2k.newchar.nationalityfield" /></td>
					<td><s:select
							name="character.nationality"
							list="nationalities"/></td>
							
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="abc" /></td>
					
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="def" /></td>
				</tr>	
				
				<tr>
					<td><s:text name="t2k.newchar.natitivelangtext" /></td>
					<td>
						<div id="NativeLanguagesContainer">
							<ul>
								<s:iterator value="character.nativeLanguages">
									<li>
										<s:textfield name="%{key}" value="%{key}" /><s:hidden name="character.nativeLanguages['%{key}']" value="%{value}" />										
									</li>								
              					</s:iterator>
              				</ul>
						</div>
					</td>
							
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="abc" /></td>
					
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="def" /></td>
				</tr>

				<s:if test="hasFieldErrors()">
				<tr>
					<td colspan="6"><s:fielderror /></td>
				</tr>
				</s:if>
				<s:if test="hasActionMessages()">
				<tr>
					<td colspan="6"><s:actionmessage /></td>
				</tr>
				</s:if>
				<s:if test="hasActionErrors()">
				<tr>
					<td colspan="6"><s:actionerror /></td>
				</tr>
				</s:if>
			</table>
		</div>
	</s:form>
</div>