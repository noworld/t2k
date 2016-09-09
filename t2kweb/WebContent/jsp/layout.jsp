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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<s:set var="debugMode" value="%{getText('t2k.debugmode')}" />

<head>
	<meta name="robots" content="noindex,nofollow" />
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title><s:text name="t2k.browsertitle" />: <tiles:getAsString name="browserTitle"/></title>
	<link rel="icon" type="image/png" href="favicon.ico" ></link>
	<link rel="stylesheet" href="<tiles:getAsString name="googleFonts"/>" ></link>	
	<link rel="stylesheet" href="<tiles:getAsString name="mainCssFile"/>" type="text/css"></link>
	<script src="<tiles:getAsString name="jqueryJsFile"/>" type="text/javascript"></script>
	<script src="<tiles:getAsString name="mainJsFile"/>" type="text/javascript"></script>
</head>

<body>

	<tiles:insertAttribute name="header" />
	
	<div class="content_box">
		<tiles:insertAttribute name="body" />
	</div>
	
	<tiles:insertAttribute name="footer" />
	
</body>	
	
</html>