package {
	import flash.display.*;
	import flash.events.*;
	import flash.text.*;
	import flash.external.ExternalInterface;

	dynamic public class Loading extends MovieClip {
		public var TIPs:TextField;
		public var tips:String;

		public function Loading() {
			addFrameScript(0, this.frame1);
			return;
		}// end function

		public function enterFrameFun(evt:Event) {
			if(this.tips == null) {
				this.tips = "正在努力加载中，请稍候……";
			}
			
			this.TIPs.text = tips;
			return;
		}// end function

		function frame1() {
			this.tips = stage.loaderInfo.parameters["tips"];
			addEventListener(Event.ENTER_FRAME, this.enterFrameFun);
			ExternalInterface.addCallback("setTips", setTips);
			trace("设置完成回调函数setTips");
			return;
		}// end function
		
		public function setTips(sTip:String):void {
			this.tips = sTip;
		}
	}
}
