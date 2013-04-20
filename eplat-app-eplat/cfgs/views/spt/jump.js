//调用flash中的方法，"my_mv"为html页中swf的id
//搭建js与flash互通的环境

var MouseOnButtonID = -1;

function Onload() {
	var tmpArr, QueryString;
	var URL = document.location.toString();
	if (URL.lastIndexOf("=") != -1) {
		QueryString = URL.substring(URL.lastIndexOf("=") + 1, URL.length);
		document.getElementById("page" + QueryString).style.display = "block";
	} else {
		document.getElementById("page1").style.display = "block";
	}
}
function OnMouseOn(id, bResize) {
	// document.getElementById("item_"+id).style.background="#eef";
	/* 改变手状 */
	var e = document.getElementById("item_" + id);
	e.style.cursor = "hand";

	/* 换图,首先扩大图片区域。防止对周围区域的挤压 */
	with (document.getElementById("img_" + id).style) {
		width = "52px";
		height = "52px"
	}
	var string = document.getElementById("img_" + id).src;
	var index = string.indexOf('.');
	var left = string.substr(0, index);
	var src = left + '2' + '.gif';
	document.getElementById("img_" + id).src = src;

	/* 显示...和详细之间的切换 */
	if (bResize == 1) {
		document.getElementById("items" + id + "_0").style.display = "none";
		document.getElementById("items" + id + "_1").style.display = "block";
	}
}

function OnMouseLeave(id, bResize) {
	// document.getElementById("item_"+id).style.background="#fff
	// url(img/bg_newsitem.png) repeat-x";
	document.getElementById("item_" + id).style.cursor = "default";
	with (document.getElementById("img_" + id).style) {
		width = "48px";
		height = "48px";
	}
	var string = document.getElementById("img_" + id).src;
	var index = string.indexOf('.');
	var left = string.substr(0, index - 1);
	var src = left + '.gif';
	document.getElementById("img_" + id).src = src;

	/* 显示...和详细之间的切换 */
	if (bResize == 1) {
		document.getElementById("items" + id + "_0").style.display = "block";
		document.getElementById("items" + id + "_1").style.display = "none";
	}
	// document.getElementById("item_"+id).style.filter="alpha(opacity=100,finishOpacity=40,style=1)";
}

// 几个分类层的显示
function OnDivExpand(id) {
	var string = document.getElementById("img" + id).src;
	var index = string.indexOf('.');
	var left = string.substr(0, index - 1);

	if (document.getElementById("Section" + id).style.display == "block") {
		document.getElementById("Section" + id).style.display = "none";
		var src = left + '0' + '.jpg';
		document.getElementById("img" + id).src = src;
	} else {
		document.getElementById("Section" + id).style.display = "block";
		var src = left + '1' + '.jpg';
		document.getElementById("img" + id).src = src;
	}
}

// 护理详情中判断是考核还是训练
function GetOperatType() {
	var strURL = parent.window.location.href;
	var index = strURL.indexOf('?');
	var strType = strURL.substr(index + 1, strURL.length - 1);
	return strType;
}

// 详细内容里按钮点击事件
// 参数id:指定条目的编号
// 参数btnType:无效
function OnButtonClick(id, btnType) {
	if (GetOperatType() == "test") {
		external.JSCommand(4, id);
		location.reload();
	} else if (GetOperatType() == "train") {
		external.JSCommand(5, id);
		location.reload();
	}
}

// 绿色按钮点击事件(详情)
// 参数id:指定条目的编号
// 参数btnType:训练为0，考核为1
function OnGreenBtnClick(id, btnType) {
	if (MouseOnButtonID != -1) {
		return;
	}
	if (btnType == 0) {
		var strTitle = document.getElementById("title" + id).innerHTML;
		parent.window.location.href = strTitle + '.html?train';
	} else if (btnType == 1) {
		var strTitle = document.getElementById("title" + id).innerHTML;
		parent.window.location.href = strTitle + '.html?test';
	}
}

// 考核层点击事件
function OnTestDivClick(id, number) {
	if (MouseOnButtonID == -1) {
		external.JSCommand(id, number);
		location.reload();
	}
}

// 训练层点击事件
function OnTrainDivClick(id, number) {
	if (MouseOnButtonID == -1)
		OnGreenBtnClick(id, number);
}

// 按钮事件
function OnBtnMouseOver(id) {
	MouseOnButtonID = id;
}
// 按钮事件
function OnBtnMouseOut(id) {
	MouseOnButtonID = -1;
}

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
}

function callExternal(str) {
	var me = thisMovie("myFlash");
	thisMovie("myFlash").FlagShow(str);
}

function gotoHomePage() {
	var rtn = fireEvent("A00");
	// alert(rtn);
	// parent.location.href = "CourseWare.html";
}

var menus = {
	"A" : "A01",
	"B" : "A02",
	"C" : "A03",
	"D" : "A04",
	"E" : "A05",
};
function gotoMenu(menu) {
	var rtn = fireEvent(menus[menu]);
}

function aFunction() {
	var rtn = fireEvent("A01");
	// alert(rtn);
	// parent.location.href = "CourseWare.html";
}

function bFunction() {
	var rtn = fireEvent("A02");
	// parent.window.location.href = "SpecTrain.html";
}

function cFunction() {
	var rtn = fireEvent("A03");
	// parent.location.href = "NurseScene.htm";
}

function dFunction() {
	var rtn = fireEvent("A04");
	// parent.window.location.href = "nurse_test.htm";
}

function eFunction() {
	var rtn = fireEvent("A05");
	// parent.window.location.href = "systom_set.html";
}

function onExitSystem() {
	var rtn = fireEvent("Z01");
}
