var currLangs;

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
	currLangs = {};
	
	var ajaxUrl = "json/NativeLanguageValues?nationalityId=" + $("#Character_character_nationality").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = $("<ul />");
		  $.each( data["languages"], function(index,val) {
			  
			  currLangs[val["id"]] = val;
			  
			  var langText = val["name"];
			  
			  if(val["targetNumber"] >= 20) {
				  langText += " (native)";
				  items.append($("<li>" + langText + "</li>"));
			  } else {
				  langText += " (" + val["targetNumber"] + ")";
				  
				  var  item = $("<li />", {
						  text: langText,						  
						  id: "item_lang_" + val["id"],
						  "class": "pendingLang"});
				  
				  var skipBtnId = 'btn_skip_'+val["id"];
				  
				  item.append($('<button/>', {
				        text: "Skip",
				        id: skipBtnId,
				        click: function () {$("#item_lang_" + val["id"]).remove(); return false;}
				    }));
				  
				  var attemptBtnId = 'btn_attempt_'+val["id"];
				  
				  item.append($('<button/>', {
				        text: "Attempt",
				        id: attemptBtnId,
				        click: function () { attemptLang(val["id"]);  return false; }
				    }));
				  				  
				  				  
				  items.append(item);
			  }			
		  });
		 
		  $("#NativeLanguagesContainer").append(items);
		  
		});
}

function attemptLang(id) {
	var attemptedLang = currLangs[id];
	var langElement = $("#item_lang_" + attemptedLang["id"]);
	langElement.children().remove().end();
	langElement.removeClass("pendingLang");
	langElement.text(attemptedLang["name"] + " (native)");
}