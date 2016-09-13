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
 	
<div id="leftNavMenu" class="leftnav">
	
	<div id="leftNavMenuTitle"><s:text name="t2k.menu.menutitle" /></div>
	<div id="leftNavCollapse"> 
		<div class="collapsebox"></div>
	</div>
		
	<ul id="leftNavList">		
		<li><a href="<s:url action="Main" />"><s:text name="rpg.menu.t2ktext" /></a></li>
		<li><a href="<s:url action="SW-Main" />"><s:text name="rpg.menu.swtext" /></a></li>
		<li><a href="<s:url action="SW-Profile" />"><s:text name="t2k.menu.profiletext" /></a></li>
		<li><a href="<s:url action="Campaigns" />"><s:text name="t2k.menu.campaignstext" /></a></li>
		<li><a href="<s:url action="ShowNewCharacter" />"><s:text name="t2k.menu.characterstext" /></a></li>
		<li><a href="<s:url action="ShowInvite" />"><s:text name="t2k.menu.invitetext" /></a></li>
	</ul>
</div>


