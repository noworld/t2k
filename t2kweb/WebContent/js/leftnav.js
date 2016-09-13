var navMenuHeight = 0;

function hideLeftNavMenu() {			
	$("#leftNavList").slideUp(400, function(){
			$("#leftNavMenu").css("border-style", "none");
			$("#leftNavList").hide();
			$("#leftNavMenuTitle").show();				
			$(".collapsebox").addClass("collapsebox_right");
		});
	$("#leftNavCollapse").off("click");
	$("#leftNavCollapse").on("click",showLeftNavMenu);
	setCookie("leftNavCollapse","collapse",1);
}

function showLeftNavMenu() {	
	$("#leftNavMenuTitle").hide();
	$("#leftNavMenu").css("border-style", "double none double none");
	$("#leftNavList").slideDown(400, function(){			
			$("#leftNavList").show();					
		});
	$("#leftNavCollapse").off("click");
	$("#leftNavCollapse").on("click",hideLeftNavMenu);
	$(".collapsebox").removeClass("collapsebox_right");
	setCookie("leftNavCollapse","expand",1);
}

$(function() {
	navMenuHeight = $("#leftNavMenu").height() + 5;	
	var collapsed = getCookie("leftNavCollapse");
	if(collapsed !== undefined && collapsed == "collapse") {
		$("#leftNavMenu").css("border-style", "none");
		$("#leftNavList").hide();
		$("#leftNavMenuTitle").show();	
		$("#leftNavCollapse").off("click");
		$("#leftNavCollapse").on("click",showLeftNavMenu);
		$("#leftNavMenuTitle").off("click");
		$("#leftNavMenuTitle").on("click",showLeftNavMenu);
		$(".collapsebox").addClass("collapsebox_right");
		
		setCookie("leftNavCollapse","collapse");
	} else {
		$("#leftNavCollapse").bind("click",hideLeftNavMenu);
		$("#leftNavMenuTitle").bind("click",showLeftNavMenu);
		$(".collapsebox").removeClass("collapsebox_right");
		setCookie("leftNavCollapse","expand");
	}
});