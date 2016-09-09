var currLangs;
var optionalLangCount;
var rollBoxes = [];
var maxBoxes = 2;
var hideRollDelay = 8000;
var hideBoxTimeout;
var rollHistory = [];

$(function() {

	bindEvents();
	popNationalitySelect(); 	
	setSavedValues();
});

function bindEvents() {
	$("#NewCharacterForm_character_faction").on("change",popNationalitySelect);
	$("#NewCharacterForm_character_nationality").on("change",popNativeLanguage);
	$("#NewCharacterSaveLink").on("click",function(){$("#NewCharacterForm").submit();});
}

function popNationalitySelect() {
		
	optionalLangCount = 0;
	
	$("#NewCharacterForm_character_nationality").find('option').remove().end();
	
	var ajaxUrl = "json/CharValues?groupName=nationalities_by_id&groupVal=" + $("#NewCharacterForm_character_faction").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = [];
		  
		  $.each( data["values"], function(key, val) {			  
			  items.push("<option value='" + key + "'>" + val + "</option>");			
		  });
		 
		  $("#NewCharacterForm_character_nationality").append(items.join(""));
		  
		}).done(setSavedNationality);
}

function setSavedNationality() {
	//Get saved nationality
	var savedNationality = $("#NewCharacterForm_selectedNationality").prop("value");
	
	//Check to see if the nationality is in the select list 
	var exists = false;
	$('#NewCharacterForm_character_nationality option').each(function(){
	    if (this.value == savedNationality) {
	        exists = true;
	        return false;
	    }
	});
	
	//If the saved nationality is available and in the list
	//then select it
	if(savedNationality >= 0 && exists) {
		$("#NewCharacterForm_character_nationality").prop("value",savedNationality);
	}
	
	//Choose the native languages based on the nationality
	popNativeLanguage();
}

function popNativeLanguage() {
	$("#NativeLanguagesContainer").children().remove().end();
	currLangs = {};
	
	var ajaxUrl = "json/NativeLanguageValues?nationalityId=" + $("#NewCharacterForm_character_nationality").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = $("<ul />");
		  $.each( data["languages"], function(index,val) {
			  
			  currLangs[val["id"]] = val;
			  
			  var lang = val["name"];
			  var langText = lang;
			  var id = val["id"];
			  
			  if(val["targetNumber"] >= 20) {
				  langText += " (native)";
				  
				  var item = $("<li/>");
				  
				  var span = $("<span/>",{text : langText});
				  
				  item.append(span);
				  
				  var hiddenInput = $("<input />",{
					  type : "hidden",
					  name : "character.nativeLanguages['" + lang + "']",
					  id : "NewCharacterForm_character_nativeLanguages_'" + lang + "'_",
					  value : id
				  });
				  
				  item.append(hiddenInput);
				  
				  items.append(item);
				  
			  } else {
				  langText += " (" + val["targetNumber"] + ")";
				  
				  var item = $("<li/>",{
					  id : "item_lang_" + id,
					  "class" : "pendingLang"
					  });
				  
				  var span = $("<span/>",{text : langText});
				  
				  item.append(span);
				  
				  var hiddenInput = $("<input />",{
					  type : "hidden",
					  name : "character.nativeLanguages['" + lang + "']",
					  id : "NewCharacterForm_character_nativeLanguages_'" + lang + "'_",
					  value : id,
				  });
				  				  
				  item.append(hiddenInput);
				  
				  var skipBtnId = 'btn_skip_' + id;
				  
				  item.append($('<button/>', {
				        text: "Skip",
				        id: skipBtnId,
				        click: function () {
				        			var langElement = $("#item_lang_" + val["id"]);
				        			langElement.children().remove().end();
				        			langElement.removeClass("pendingLang");
									langElement.addClass("failedLang");
				        			return false;
				        		}
				    }));
				  
				  var attemptBtnId = 'btn_attempt_' + id;
				  
				  item.append($('<button/>', {
				        text: "Attempt",
				        id: attemptBtnId,
				        click: function () {
				        			attemptLang(val["id"]); 
				        			return false;
				        		}
				    }));
				  				  
				  optionalLangCount++;
				  items.append(item);
			  }			
		  });
		 
		  $("#NativeLanguagesContainer").append(items);
		  
		});
}

function attemptLang(id) {
	
	var result = "";
	
	$.getJSON("json/RollTen", function(data) {		
		var attemptedLang = currLangs[id];
		var langElement = $("#item_lang_" + attemptedLang["id"]);
		if(data["roll"] <= attemptedLang["targetNumber"]) {
			langElement.find("button").remove().end();
			langElement.removeClass("pendingLang");
			langElement.find("span").text(attemptedLang["name"] + " (native)");
			
			var parent = langElement.parent(); 
			parent.children().each(function(idx) {
				if($(this).text().indexOf("native") < 0) {
					$(this).remove();
				}
			});
			
			result = "Success!";
		} else {
			langElement.children().remove().end();
			langElement.removeClass("pendingLang");
			langElement.addClass("failedLang");
			langElement.text(attemptedLang["name"]);
			optionalLangCount--;
			result = "Fail";
		}	
		
		showRoll(data["roll"], "You rolled " +
				data["roll"] +
				" against " +
				attemptedLang["targetNumber"] +
				" to receive " + attemptedLang["name"] + 
				" as a native language. (" + result + ")");
		
		if(optionalLangCount <= 0) {
			var parent = langElement.parent(); 
			parent.children().each(function(idx) {
				if($(this).text().indexOf("native") < 0) {
					$(this).remove();
				}
			});
			
		}
	});

}

function showRoll(roll, message) {	
	
	if(rollBoxes.length == maxBoxes) {
		var lastBox = rollBoxes.shift();
		rollHistory.push(lastBox);
		lastBox.animate({
			left: "-=200",
			opacity: 0.0
		},function(){lastBox.remove();});
		
	}
	
	for(var i = rollBoxes.length - 1; i >= 0; i--) {
		if(rollBoxes[i] !== undefined) {
			rollBoxes[i].animate({
				top: "+=225"
			});
		}
	}
	
	var rollBox = $("<div></div>",{
		text: message,		
		"class":"roll_display"
		});
	
	rollBoxes.push(rollBox);

	$("body").append(rollBox);

	rollBox.animate({
			left: "+=200"
	});
	
	clearTimeout(hideBoxTimeout);
	hideBoxTimeout = setTimeout(hideRoll,hideRollDelay);

}

function hideRoll() {
	for(var i = rollBoxes.length - 1; i >= 0; i--) {
		if(rollBoxes[i] !== undefined) {
			rollHistory.push(rollBoxes[i]);
			rollBoxes[i].animate({
				left: "-=200",
				opacity: 0.0
			});
		}
	}
	
	rollBoxes = [];
}