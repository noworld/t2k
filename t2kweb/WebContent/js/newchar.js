$(function() {

	bindEvents();
	popNationalitySelect(); 	
	
});

function bindEvents() {
	$("#Character_selectedFaction").on("change",popNationalitySelect);
	$("#Character_character_nationality").on("change",popNativeLanguage);
}

function popNationalitySelect() {
		
	$("#Character_character_nationality").find('option').remove().end();
	
	var ajaxUrl = "json/CharValues?groupName=nationalities_by_id&groupVal=" + $("#Character_selectedFaction").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = [];
		  
		  $.each( data["values"], function(key, val) {
		    items.push( "<option value='" + key + "'>" + val + "</option>" );
		  });
		 
		  $("#Character_character_nationality").append(items.join(""));
		  
		  popNativeLanguage();
		  
		});
}

function popNativeLanguage() {
	$("#NativeLanguagesContainer").children().remove().end();
	
	var ajaxUrl = "json/CharValues?groupName=native_languages&groupVal=" + $("#Character_character_nationality").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = [];
		  items.push("<ul>");
		  $.each( data["values"], function(key, val) {
		    items.push( "<li value='" + key + "'>" + val + "</li>" );
		  });
		  items.push("</ul>");
		 
		  $("#NativeLanguagesContainer").append(items.join(""));
		  
		});
}