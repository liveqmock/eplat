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

	/* 保持DIV层不超出屏幕外的可见性 先获得层的下边高度，再获取窗口的下边高度 判断并调整滚动条 */
	/* 获取到的pt_Bottom值为相对于文档左上角的绝对高度 */
	// var pt_Bottom = e.offsetTop+e.offsetHeight;
	// while(e=e.offsetParent)
	// {
	// pt_Bottom+=e.offsetTop;
	// }
	// var dis_x = pt_Bottom-parseInt(document.documentElement.clientHeight);
	// if(dis_x > 0)
	// {
	// window.scrollBy(0,dis_x);
	// }
	// for test
	// alert("clientHeight="+clientHeight+"windowHeight="+windowHeight);
	/* 当前窗口的高度 */
	/* 当前层为相对定位时 层的扩张会导致窗口高度增加 */
	/* 当前层为绝对定位时 层的扩张不会导致改变窗口高度 */
	/*
	 * XHTML中用 document.documentElement.clientHeight
	 * 代替document.body.clientHeight XHTML中后者取得的是BODY文档的高度
	 */
	// var clientHeight = parseInt(document.body.clientHeight);
	/* 当前文档的总高度 */
	// document.body.scrollHeight
	/* 当前屏幕窗口的总高度 */
	// var windowHeight =parseInt(window.screen.availHeight);
	// for test
	// document.getElementById("item_"+id).style.filter="alpha(opacity=100,finishOpacity=100,style=1)";
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

// 写标题
function writeTitle(CurPage) {
	var title = CurPage;
	if (CurPage == "CourseWare") {
		callExternal("A");
		title = "系 统 课 件 列 表";
	} else if (CurPage == "NurseScene") {
		callExternal("C");
		title = "急 救 案 例 训 练";
	} else if (CurPage == "SpecTrain") {
		callExternal("B");
		title = "专 项 训 练";
	} else if (CurPage == "nurse_test") {
		callExternal("D");
		title = "急 救 案 例 考 核";
	} else if (CurPage == "systom_set") {
		callExternal("E");
		title = "系 统 设 置";
	}
	document.write(title);
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

function aFunction() {
	parent.location.href = "CourseWare.htm";
}

function bFunction() {
	parent.window.location.href = "SpecTrain.html";
}

function cFunction() {
	parent.location.href = "NurseScene.htm";
}

function dFunction() {
	parent.window.location.href = "nurse_test.htm";
}

function eFunction() {
	parent.window.location.href = "systom_set.html";
}

function CloseFun() {
	external.JSCommand(0, 0);
}

// function getCookie(name)
// {
// var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
// if(arr !=null) return unescape(arr[2]); return null;
// }
//
// function setCookie(name,value)
// {
// var Days = 1; //此 cookie 将被保存 1 天
// var exp = new Date();
// exp.setTime(exp.getTime() + Days*24*60*60*1000);
// document.cookie = name + "="+ escape (value) + ";expires=" +
// exp.toGMTString();
// }

// open具体参数介绍
// url为网站地址
// "myPage"为弹出的该窗口的名称，可以省略为""
// height、width是窗口的大小
// toolbar指定是否有工具栏
// top窗口距离屏幕上方的象素值
// left窗口距离屏幕左侧的象素值
// toolbar是否显示工具栏，yes为显示，下同
// menubar、scrollbars表示菜单栏和滚动栏
// resizable是否允许改变窗口大小
// location是否显示地址栏
// status是否显示状态栏内的信息（通常是文件已经打开）
// window.open(url,"myPage","height=400, width=600, top=0, left=0, toolbar=no,
// menubar=no, scrollbars=no, resizable=no, location=no, status=no");
