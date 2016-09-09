var currLangs;
var rollBoxes = [];
var maxBoxes = 2;
var hideRollDelay = 8000;
var hideBoxTimeout;
var rollHistory = [];

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
		    items.push("<option value='" + key + "'>" + val + "</option>");
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
				        click: function () {
				        			var langElement = $("#item_lang_" + val["id"]);
				        			langElement.children().remove().end();
				        			langElement.removeClass("pendingLang");
									langElement.addClass("failedLang");
				        			return false;
				        		}
				    }));
				  
				  var attemptBtnId = 'btn_attempt_'+val["id"];
				  
				  item.append($('<button/>', {
				        text: "Attempt",
				        id: attemptBtnId,
				        click: function () {
				        			attemptLang(val["id"]); 
				        			return false;
				        		}
				    }));
				  				  
				  				  
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
			langElement.children().remove().end();
			langElement.removeClass("pendingLang");
			langElement.text(attemptedLang["name"] + " (native)");
			result = "Success!";
		} else {
			langElement.children().remove().end();
			langElement.removeClass("pendingLang");
			langElement.addClass("failedLang");
			result = "Fail";
		}	
		
		showRoll(data["roll"], "You rolled " +
				data["roll"] +
				" against " +
				attemptedLang["targetNumber"] +
				" to receive " + attemptedLang["name"] + 
				" as a native language. (" + result + ")");
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