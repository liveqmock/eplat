//����flash�еķ�����"my_mv"Ϊhtmlҳ��swf��id
//�js��flash��ͨ�Ļ���

var MouseOnButtonID = -1;

function Onload()
{
	var tmpArr,QueryString;
	var URL = document.location.toString();
	if(URL.lastIndexOf("=")!=-1)
	{
		QueryString= URL.substring(URL.lastIndexOf("=")+1,URL.length);
		document.getElementById("page"+QueryString).style.display = "block";
	}
	else
	{
		document.getElementById("page1").style.display = "block";
	}
}
function OnMouseOn(id,bResize)
{
  //document.getElementById("item_"+id).style.background="#eef";
  /*�ı���״*/
  var e = document.getElementById("item_"+id);
  e.style.cursor="hand";
  
  /*��ͼ,��������ͼƬ���򡣷�ֹ����Χ����ļ�ѹ*/
  with(document.getElementById("img_"+id).style)
  {
    width="52px";
    height="52px"
  }
  var string = document.getElementById("img_"+id).src;
  var index = string.indexOf('.');
  var left = string.substr(0,index);
  var src =left + '2' + '.gif';
  document.getElementById("img_"+id).src =src;
  
  /*��ʾ...����ϸ֮����л�*/
  if(bResize==1)
  {
    document.getElementById("items"+id+"_0").style.display="none";
    document.getElementById("items"+id+"_1").style.display="block";
  }
   
  /*����DIV�㲻������Ļ��Ŀɼ��� �Ȼ�ò���±߸߶ȣ��ٻ�ȡ���ڵ��±߸߶� �жϲ�����������*/
  /*��ȡ����pt_BottomֵΪ������ĵ����Ͻǵľ��Ը߶�*/
//  var pt_Bottom = e.offsetTop+e.offsetHeight; 
//  while(e=e.offsetParent)   
//  {   
//      pt_Bottom+=e.offsetTop;   
//  }
//  var dis_x = pt_Bottom-parseInt(document.documentElement.clientHeight);
//  if(dis_x > 0)
//  {
//	 window.scrollBy(0,dis_x); 
//  }
  
  //for test
  //alert("clientHeight="+clientHeight+"windowHeight="+windowHeight);
  /*��ǰ���ڵĸ߶�*/
  /*��ǰ��Ϊ��Զ�λʱ ������Żᵼ�´��ڸ߶�����*/
  /*��ǰ��Ϊ���Զ�λʱ ������Ų��ᵼ�¸ı䴰�ڸ߶�*/
  /*XHTML���� document.documentElement.clientHeight ����document.body.clientHeight XHTML�к���ȡ�õ���BODY�ĵ��ĸ߶�*/
  //var clientHeight = parseInt(document.body.clientHeight);
  /*��ǰ�ĵ����ܸ߶�*/
  //document.body.scrollHeight
  /*��ǰ��Ļ���ڵ��ܸ߶�*/
  //var windowHeight =parseInt(window.screen.availHeight); 
  
  //for test
 // document.getElementById("item_"+id).style.filter="alpha(opacity=100,finishOpacity=100,style=1)";
}

function OnMouseLeave(id,bResize)
{
 // document.getElementById("item_"+id).style.background="#fff url(img/bg_newsitem.png) repeat-x";
  document.getElementById("item_"+id).style.cursor="default";
   with(document.getElementById("img_"+id).style)
  {
    width="48px";
    height="48px";
  }
  var string = document.getElementById("img_"+id).src;
  var index = string.indexOf('.');
  var left = string.substr(0,index-1);
  var src =left + '.gif';
  document.getElementById("img_"+id).src =src;
  
 /*��ʾ...����ϸ֮����л�*/
 if(bResize==1)
 {
  document.getElementById("items"+id+"_0").style.display="block";
  document.getElementById("items"+id+"_1").style.display="none";
 }
  //  document.getElementById("item_"+id).style.filter="alpha(opacity=100,finishOpacity=40,style=1)";
}

//������������ʾ
function OnDivExpand(id)
{
	var string = document.getElementById("img"+id).src;
    var index = string.indexOf('.');
    var left = string.substr(0,index-1);

	if(document.getElementById("Section"+id).style.display=="block")
	{
		document.getElementById("Section"+id).style.display="none";
		var src =left + '0' + '.jpg';
        document.getElementById("img"+id).src =src; 
	}
	else
	{
		document.getElementById("Section"+id).style.display="block";
		var src =left + '1' + '.jpg';
        document.getElementById("img"+id).src =src; 
	}
}

//д����
function writeTitle(CurPage)
{  
    var title = CurPage;
    if(CurPage=="CourseWare")
    {
      callExternal("A");
      title="ϵ ͳ �� �� �� ��";
	 }
    else if(CurPage=="NurseScene")
	{
	callExternal("C");
	title="�� �� �� �� ѵ ��";
	}
	else if(CurPage=="SpecTrain")
	{
	 callExternal("B");
	 title="ר �� ѵ ��";
	}
	else if(CurPage=="nurse_test")
	{
	 callExternal("D");
	 title="�� �� �� �� �� ��";
	}
	else if(CurPage=="systom_set")
	{
	 callExternal("E");
	 title="ϵ ͳ �� ��";
	}
	document.write(title);
}

//�����������ж��ǿ��˻���ѵ��
function GetOperatType()
{
	var strURL = parent.window.location.href;
	var index = strURL.indexOf('?');
    var strType = strURL.substr(index+1,strURL.length-1);
	return strType;
}


//��ϸ�����ﰴť����¼�
//����id:ָ����Ŀ�ı��
//����btnType:��Ч
function OnButtonClick(id,btnType)
{	
	 if(GetOperatType()=="test")
	 {
		 external.JSCommand(4,id);
		 location.reload();
	 }
	 else if(GetOperatType()=="train")
	 {
		 external.JSCommand(5,id);
		 location.reload();
	 }
}


//��ɫ��ť����¼�(����)
//����id:ָ����Ŀ�ı��
//����btnType:ѵ��Ϊ0������Ϊ1
function OnGreenBtnClick(id,btnType)
{
	if(MouseOnButtonID != -1)
	{
		return;
	}
	if(btnType == 0)
	{	
		  var strTitle = document.getElementById("title"+id).innerHTML;
		  parent.window.location.href = strTitle+'.html?train';
	}
	else if(btnType == 1)
	{
		var strTitle = document.getElementById("title"+id).innerHTML;
		parent.window.location.href = strTitle+'.html?test';
	}
}

//���˲����¼�
function OnTestDivClick(id,number)
{
	if(MouseOnButtonID == -1)
	{
		external.JSCommand(id,number);
		location.reload();
	}
}

//ѵ�������¼�
function OnTrainDivClick(id,number)
{
	if(MouseOnButtonID == -1)
	OnGreenBtnClick(id,number);
}

//��ť�¼�
function OnBtnMouseOver(id)
{
	MouseOnButtonID = id;
}
//��ť�¼�
function OnBtnMouseOut(id)
{
	MouseOnButtonID = -1;
}

function thisMovie(movieName)
{
  if (navigator.appName.indexOf("Microsoft") != -1)
  {
    return window[movieName];
  }
  else
  {
    return document[movieName];
  }
}


function callExternal(str) 
{
  var me=thisMovie("myFlash");
  thisMovie("myFlash").FlagShow(str);
}

function aFunction()
{
	parent.location.href = "CourseWare.htm"; 
}

function bFunction()
{
   	parent.window.location.href = "SpecTrain.html";
}

function cFunction()
{
 parent.location.href = "NurseScene.htm"; 
}

function dFunction()
{
   parent.window.location.href = "nurse_test.htm"; 
}

function eFunction()
{
  parent.window.location.href ="systom_set.html";
}


function CloseFun()
{
	external.JSCommand(0,0);
}


//function getCookie(name)
//{
//����var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
//����if(arr !=null) return unescape(arr[2]); return null;
//}
//
//function setCookie(name,value)
//{
//����var Days = 1; //�� cookie �������� 1 ��
//����var exp��= new Date();
//����exp.setTime(exp.getTime() + Days*24*60*60*1000);
//����document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
//}


// open�����������
// urlΪ��վ��ַ
// "myPage"Ϊ�����ĸô��ڵ����ƣ�����ʡ��Ϊ""
// height��width�Ǵ��ڵĴ�С
// toolbarָ���Ƿ��й�����
// top���ھ�����Ļ�Ϸ�������ֵ 
// left���ھ�����Ļ��������ֵ
// toolbar�Ƿ���ʾ��������yesΪ��ʾ����ͬ
// menubar��scrollbars��ʾ�˵����͹�����
// resizable�Ƿ�����ı䴰�ڴ�С
// location�Ƿ���ʾ��ַ��
// status�Ƿ���ʾ״̬���ڵ���Ϣ��ͨ�����ļ��Ѿ��򿪣�
//window.open(url,"myPage","height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");

