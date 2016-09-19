var currLangs;
var optionalLangCount = 0;
var rollBoxes = [];
var maxBoxes = 2;
var hideRollDelay = 8000;
var hideBoxTimeout;
var rollHistory = [];
var attributePointsRemaining = 0;
var adjustedAttributePoints = 32;
var rollBoxFirstOffset = "450px";

$(function() {
	
	addJsFiles() ;
	bindEvents();
	popNationalitySelect();
	setupAttributes();
	
});

function addJsFiles() {
	$("head").append($("<script></script>",{
		src: "js/chargencareers.js",
		type: "text/javascript",
	}));
}

function bindEvents() {
	$("#NewCharacterForm_character_faction").on("change",changeFaction);
	$("#NewCharacterForm_character_nationality").on("change",changeNationality);
	$("#NewCharacterSaveLink1").on("click",saveForm);
	$("#NewCharacterSaveLink2").on("click",saveForm);
	$("#AttributeReset").on("click",resetAttributes);	
}

function saveForm() {
	$("#NewCharacterForm").submit();
	return false;
}

function changeFaction() {
	$("#NewCharacterForm_languagesFinished").prop("value","false");
	popNationalitySelect();
}

function changeNationality() {
	$("#NewCharacterForm_languagesFinished").prop("value","false");
	popNativeLanguageSelect();
}

function popNationalitySelect() {
	
	$("#NewCharacterForm_character_nationality").find('option').remove().end();
	
	
	var ajaxUrl = "json/CharValues?groupName=nationalities_by_id&groupVal=" + $("#NewCharacterForm_character_faction").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = [];
		  
		  $.each( data["values"], function(key, val) {			  
			  items.push("<option value='" + key + "'>" + val + "</option>");			
		  });
		 
		  $("#NewCharacterForm_character_nationality").append(items.join(""));
		  
		}).done(setSavedValues);
}

function setupAttributes() {
		$(".basic_attributes_container").find("[id^=NewCharacterForm_character_]").each(function(idx,el) {			
			var hiddenElement = $(el);
			var regex = /NewCharacterForm_character_(.*)/;
			var attr = (hiddenElement.prop("id").match(regex))[1];			
			var rollBtnId = 'btn_roll_' + attr;
			
			if($("#NewCharacterForm_character_" + attr).prop("value") === undefined
					|| $("#NewCharacterForm_character_" + attr).prop("value").trim() === ""
					|| $("#NewCharacterForm_character_" + attr).prop("value") == 0) {
				$("#" + attr + "_roll").text("[ _ ]");
				
				hiddenElement.parent().append($('<button/>', {
					text: "Roll",
					id: rollBtnId,
					click: function () {
		
						var ajaxUrl = "json/RollAttribute";
						
						$.getJSON(ajaxUrl, function(data) {
							$("#" + attr + "_roll").text("[ " + data["roll"] + " ]");
							$("#NewCharacterForm_character_" + attr).prop("value",data["roll"]);
							
							var message = "Your " + attr + " roll was: " + data["roll"] + " [(" + data["dice"].join("+") + ")-2]";						
							
							showRoll(data["roll"], message, rollBoxFirstOffset);
							
							setAttributeTotal();
							
							$("#" + rollBtnId).remove().end();
						});
						
						return false;
					}
				}));
			} else {
				$("#" + attr + "_roll").text("[ " + $("#NewCharacterForm_character_" + attr).prop("value") + " ]");
			}
			  
	});
		
	setAttributeTotal();
}

function setSavedValues() {
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
	
	if($("#NewCharacterForm_languagesFinished").prop("value") === "true") {
		popNativeLanguagesStatic();
	} else {
		//Choose the native languages based on the nationality
		popNativeLanguageSelect();
	}
	
}

function popNativeLanguagesStatic() {
	var items = $("<ul />");
	var regex = /\['(.*)'\]/;
	$("[id^=NewCharacterForm_selectedLanguages_]").each(function(idx,el) {
		var lang = $(el);
		var name = (lang.prop("name").match(regex))[1];
		var langText = name + " (native)";
		var id =  lang.prop("value");
		var item = $("<li/>");
		  
		var span = $("<span/>",{text : langText});
		  
		item.append(span);
		  
		var hiddenInput = $("<input />",{
			type : "hidden",
			name : "character.nativeLanguages['" + name + "']",
			id : "NewCharacterForm_character_nativeLanguages_'" + name + "'_",
			value : id
		});
		  
		item.append(hiddenInput);
		 
		items.append(item);
	});
			
	$("#NativeLanguagesContainer").append(items);
}

function popNativeLanguageSelect() {
	$("#NativeLanguagesContainer").children().remove().end();
	currLangs = {};
	optionalLangCount = 0;
	
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
					  "class" : "pending_lang"
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
				        			langElement.children("input").remove().end();
				        			langElement.children("button").remove().end();
				        			langElement.removeClass("pending_lang");
									langElement.addClass("failed_lang");
									optionalLangCount--;
									checkLanguagesFinished();
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
			  
			  if(optionalLangCount > 0) {
				  $("#NewCharacterForm_languagesFinished").prop("value","false");
			  } else {
				  $("#NewCharacterForm_languagesFinished").prop("value","true");
			  }
		  });
		 
		  $("#NativeLanguagesContainer").append(items);
		  
		});
}

function popBgSkillsSelect() {
	
	$("#NewCharacterForm_character_nationality").find('option').remove().end();
	
	
	var ajaxUrl = "json/CharValues?groupName=nationalities_by_id&groupVal=" + $("#NewCharacterForm_character_faction").val();
	
	$.getJSON(ajaxUrl, function(data) {
		
		  var items = [];
		  
		  $.each( data["values"], function(key, val) {			  
			  items.push("<option value='" + key + "'>" + val + "</option>");			
		  });
		 
		  $("#NewCharacterForm_character_nationality").append(items.join(""));
		  
		}).done(setSavedValues);
}

function attemptLang(id) {
	
	var result = "";
	
	$.getJSON("json/RollTen", function(data) {		
		var attemptedLang = currLangs[id];
		var langElement = $("#item_lang_" + attemptedLang["id"]);
		if(data["roll"] <= attemptedLang["targetNumber"]) {
			langElement.find("button").remove().end();
			langElement.removeClass("pending_lang");
			langElement.find("span").text(attemptedLang["name"] + " (native)");
			
			var parent = langElement.parent(); 
			parent.children().each(function(idx) {
				if($(this).text().indexOf("native") < 0) {
					$(this).remove();
				}
			});
			
			$("#NewCharacterForm_languagesFinished").prop("value","true");
			
			result = "Success!";
			
		} else {
			langElement.children().remove().end();
			langElement.removeClass("pending_lang");
			langElement.addClass("failed_lang");
			langElement.text(attemptedLang["name"]);
			optionalLangCount--;
			result = "Fail";
		}	
		
		showRoll(data["roll"], "You rolled " +
				data["roll"] +
				" against " +
				attemptedLang["targetNumber"] +
				" to receive " + attemptedLang["name"] + 
				" as a native language. (" + result + ")",
				rollBoxFirstOffset);
		
		checkLanguagesFinished();
	});

}

function checkLanguagesFinished() {
	if(optionalLangCount <= 0) {
		$("#NativeLanguagesContainer ul").children().each(function(idx,el) {
			if($(el).text().indexOf("native") < 0) {
				$(el).remove();
			}
		});
		$("#NewCharacterForm_languagesFinished").prop("value","true");
	}
}

function showRoll(roll, message, offset) {	
	
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
		"class":"roll_display",
		style: "top: " + offset + ";"
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

function setAttributeTotal() {
	
	var attrTotal = 0;
	
	var str = parseInt($("#NewCharacterForm_character_strength").prop("value"));
	if(!isNaN(str)) {
		attrTotal += str;
	}
	
	var agl = parseInt($("#NewCharacterForm_character_agility").prop("value"));
	if(!isNaN(agl)) {
		attrTotal += agl;
	}
	
	var con = parseInt($("#NewCharacterForm_character_constitution").prop("value"));
	if(!isNaN(con)) {
		attrTotal += con;
	}
	
	var intel = parseInt($("#NewCharacterForm_character_intelligence").prop("value"));
	if(!isNaN(intel)) {
		attrTotal += intel;
	}
	
	var edu = parseInt($("#NewCharacterForm_character_education").prop("value"));
	if(!isNaN(edu)) {
		attrTotal += edu;
	}
	
	var chr = parseInt($("#NewCharacterForm_character_charisma").prop("value"));
	if(!isNaN(chr)) {
		attrTotal += chr;
	}
	
	$("#AttributeTotalVal").text("[" + attrTotal + " ]");
	
	attributePointsRemaining = adjustedAttributePoints - attrTotal;
	
	if(attributePointsRemaining < 0) {
		attributePointsRemaining = 0;
	}
	
	$("#AttributeAdjustmentPoints").text("[" + attributePointsRemaining + " ]");
}

function resetAttributes() {
	$(".basic_attributes_container span").each(function (idx,el){
		$(el).text("[ _ ]");
	});
	
	$(".basic_attributes_container > input[id^=NewCharacterForm_character_]").each(function (idx,el){
		$(el).prop("value",0);
	});
	
	$(".basic_attributes_container button[id^=btn_roll_]").remove().end();
	
	$("#NewCharacterForm_character_strength").prop("value",0);
	$("#NewCharacterForm_character_agility").prop("value",0);
	$("#NewCharacterForm_character_constitution").prop("value",0);
	$("#NewCharacterForm_character_intelligence").prop("value",0);
	$("#NewCharacterForm_character_education").prop("value",0);
	$("#NewCharacterForm_character_charisma").prop("value",0);
	
	setupAttributes();
	return false;
}