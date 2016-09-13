var sizeIncrease = 24;

$(function() {
	
	formatSavageWorldsTitle();
	
});

function formatSavageWorldsTitle() {
	var fontSizeRegex = /([0-9]+).*/;
	
	$(".header").each(function(idx,element){
		
		var el = $(element);
		var text = el.text().trim();
		var fontSize = el.css("font-size").trim();
		var size = fontSize.match(fontSizeRegex);
		
		if(size.length > 0) {		
			size = parseInt(size[size.length-1]);
			size += sizeIncrease; //Compensates for the fact that otherwise all letters would be smaller than the original size
			
			if(!isNaN(size)) {			
				var items = [];		
				for(var i = 0; i < text.length; i++) {
					var item = $("<span />",{
						text : text.charAt(i),						
						style :"font-size:" + (size - (i * 3)) + "px;" +
								"padding-top: " + (i * 20) + "px;" +
								"vertical-align: top;" +								
								"color: FireBrick;" + 
								"text-shadow: -2px 3px 0px rgba(0, 0, 0, 1);"
								
					});
					
					items.push(item);
				}
								
				el.text("");
				el.append(items);
				

			}
		}
	});
}