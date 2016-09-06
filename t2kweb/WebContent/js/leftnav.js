var navMenuHeight = 0;

function hideLeftNavMenu() {			
	$("#leftNavList").slideUp(400, function(){
			$("#leftNavMenu").css("border-style", "none");
			$("#leftNavList").hide();
			$("#leftNavMenuTitle").show();				
		});
	$("#leftNavCollapse").off("click");
	$("#leftNavCollapse").on("click",showLeftNavMenu);

}

function showLeftNavMenu() {	
	$("#leftNavMenuTitle").hide();
	$("#leftNavMenu").css("border-style", "double none double none");
	$("#leftNavList").slideDown(400, function(){			
			$("#leftNavList").show();			
		});
	$("#leftNavCollapse").off("click");
	$("#leftNavCollapse").on("click",hideLeftNavMenu);
}

$(function() {
	navMenuHeight = $("#leftNavMenu").height() + 5;
	$("#leftNavCollapse").bind("click",hideLeftNavMenu);
	$("#leftNavMenuTitle").bind("click",showLeftNavMenu);
});