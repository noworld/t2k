$(function() {

	bindEvents();
	popNationalitySelect(); 	
	
	
});

function bindEvents() {
	$("#Character_selectedFaction").on("change",popNationalitySelect);
}

function popNationalitySelect() {
		
	$("#Character_character_nationality").find('option').remove().end();
	
	var ajaxUrl = "json/CharValues?groupName=nationalities_by_id&groupVal=" + $("#Character_selectedFaction").val();
	
	$.getJSON(ajaxUrl, function( data ) {
		
		  var items = [];
		  
		  $.each( data["values"], function( key, val ) {
		    items.push( "<option value='" + key + "'>" + val + "</option>" );
		  });
		 
		  $("#Character_character_nationality").append(items.join(""));
		  
		});
}