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

$(function() {
	//alert("Load!");
});

function setCookie(name, value) {
	document.cookie = name + "=" + value;
}

function setCookie(name, value, expdays) {
	var d = new Date();
    d.setTime(d.getTime() + (expdays*24*60*60*1000));
    var exp = "expires="+ d.toUTCString();
    document.cookie = name + "=" + value + "; " + exp;
}

function getCookie(cname) {
	 var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length,c.length);
	        }
	    }
	    return "";
}

function deleteCookie(name) {
	document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
}

