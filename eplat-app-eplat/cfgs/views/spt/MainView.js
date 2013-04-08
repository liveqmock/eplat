/**
 * INIT
 */
/*
$(document).ready(function() {
	$(".item").hover(function(e) {
		$(this).css("cursor", "pointer");
		$(this).addClass("item_over");
		// $(this).corner();
		
		var img = $("#img_" + $(this).attr("id").split("_")[1]);
		img.attr("src", img.attr("src").replace("-ico-1", "-ico-2"));
	}, function(e) {
		$(this).css("cursor", "auto");
		$(this).removeClass("item_over");
		// $(this).uncorner();
		
		var img = $("#img_" + $(this).attr("id").split("_")[1]);
		img.attr("src", img.attr("src").replace("-ico-2", "-ico-1"));
	}).click(function() {
		app.doShowPpt($(this).attr("id").split("_")[1]);
	});
});
*/

function flashObject(name) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[name];
	} else {
		return document[name];
	}
}
