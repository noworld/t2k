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
		
	<s:form id="NewCharacterForm" action="NewCharacter" method="post" validate="true">
		<s:token />

		<s:url id="charListUrl" action="Characters" />
		
		<div class="char_sheet_box">
			<div class="char_subtitle">
				<s:text name="t2k.newchar.title" />
			</div>
			
			<s:hidden name="selectedNationality" />
			<s:hidden name="languagesFinished" />
			<s:iterator value="selectedLanguages">
				<s:hidden name="selectedLanguages['%{key}']" value="%{value}" />
			</s:iterator>
			
			<div class="char_sheet_left">
				<table class="form_table">
				
				<tr>
					<td><s:text name="t2k.newchar.namefield" /></td>
					<td><s:textfield name="character.name" /></td>
	
				</tr>
				
				<tr>
					<td><s:text name="t2k.newchar.genderfield" /></td>
					<td><s:select
							name="character.gender"
							list="genders"/></td>

				</tr>
				
				<tr>
					<td><s:text name="t2k.newchar.factionfield" /></td>
					<td><s:select
							name="character.faction"
							list="factions"/></td>
		
				</tr>	
				
				<tr>
					<td><s:text name="t2k.newchar.nationalityfield" /></td>
					<td><s:select
							name="character.nationality"
							list="nationalities"/></td>

				</tr>	
				
				<tr>
					<td><s:text name="t2k.newchar.natitivelangtext" /></td>
					<td>
						<div id="NativeLanguagesContainer"></div>
					</td>

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
			
			<div class="char_sheet_right">
				<table class="form_table">
				
				<tr>

					<td><s:text name="t2k.newchar.basicattributestext" /></td>
					<td><span class="attr_desc"><s:text name="t2k.newchar.basicattributesdesc" /></span></td>
					
				</tr>
				
				<tr>

					<td><span class="attr_title"><s:text name="t2k.newchar.attributetext" /></span></td>
					<td>
						<span class="attr_title"><s:text name="t2k.newchar.rolltext" /></span>
						<span class="attr_title"><s:text name="t2k.newchar.adjtext" /></span>
						<span class="attr_title"><s:text name="t2k.newchar.finaltext" /></span>
					</td>

				</tr>
				
				<tr>

					<td><span class="attr_name"><s:text name="t2k.newchar.strtext" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="strength_roll">[ _ ]</span>
							<span class="attr_title" id="strength_adj">[ _ ]</span>
							<span class="attr_title" id="strength_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.strength" />
						</div>
					</td>
	
				</tr>	
				
				<tr>

					<td><span class="attr_name"><s:text name="t2k.newchar.agltext" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="agility_roll">[ _ ]</span>
							<span class="attr_title" id="agility_adj">[ _ ]</span>
							<span class="attr_title" id="agility_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.agility" />
						</div>
					</td>

				</tr>	
				
				<tr>

					<td><span class="attr_name"><s:text name="t2k.newchar.context" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="constitution_roll">[ _ ]</span>
							<span class="attr_title" id="constitution_adj">[ _ ]</span>
							<span class="attr_title" id="constitution_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.constitution" />
						</div>
					</td>

				</tr>
				
				<tr>

					<td><span class="attr_name"><s:text name="t2k.newchar.inttext" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="intelligence_roll">[ _ ]</span>
							<span class="attr_title" id="intelligence_adj">[ _ ]</span>
							<span class="attr_title" id="intelligence_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.intelligence" />
						</div>
					</td>

				</tr>
				
				<tr>
				
					<td><span class="attr_name"><s:text name="t2k.newchar.edutext" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="education_roll">[ _ ]</span>
							<span class="attr_title" id="education_adj">[ _ ]</span>
							<span class="attr_title" id="education_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.education" />
						</div>
					</td>

				</tr>
				
				<tr>
		
					<td><span class="attr_name"><s:text name="t2k.newchar.chrtext" /></span></td>
					<td>
						<div class="basic_attributes_container">
							<span class="attr_title" id="charisma_roll">[ _ ]</span>
							<span class="attr_title" id="charisma_adj">[ _ ]</span>
							<span class="attr_title" id="charisma_final">[ _ ]</span>
							<s:textfield type="hidden" name="character.charisma" />
						</div>
					</td>

				</tr>
				
				<tr>

					<td><span class="attr_name"><s:text name="t2k.newchar.attrtotaltext" /></span></td>
					<td class="char_sheet_divider">
						<div class="basic_attributes_container">
							<span class="attr_title" id="AttributeTotalVal">[ _ ]</span>
							<span class="attr_title" id="AttributeAdjustmentPoints">[ _ ]</span>
							<span class="attr_title" id="AttributeAdjustmentPlaceholder">[ _ ]</span>
							<button id="AttributeReset">Reset</button>
						</div>						
					</td>

				</tr>
				
				<tr>

					<td colspan="2"><span class="attr_title char_sheet_note"><s:text name="t2k.newchar.rolldesc" /></span></td>
					<td style="display:none;"></td>	<!-- Required to keep the nth-child css working -->	

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