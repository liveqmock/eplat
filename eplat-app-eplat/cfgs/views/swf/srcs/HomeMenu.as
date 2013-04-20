package {
	import flash.display.*;
	import flash.events.*;
	import flash.external.*;

	public class HomeMenu extends MovieClip {
		public var aBtn:SimpleButton;
		public var bBtn:SimpleButton;
		public var cBtn:SimpleButton;
		public var dBtn:SimpleButton;
		public var eBtn:SimpleButton;

		public function HomeMenu() {
			addFrameScript(0, frame1);
			return;
		}// end function

		function frame1() {
			aBtn.addEventListener(MouseEvent.MOUSE_DOWN, aFun);
			bBtn.addEventListener(MouseEvent.MOUSE_DOWN, bFun);
			cBtn.addEventListener(MouseEvent.MOUSE_DOWN, cFun);
			dBtn.addEventListener(MouseEvent.MOUSE_DOWN, dFun);
			eBtn.addEventListener(MouseEvent.MOUSE_DOWN, eFun);
			return;
		}// end function
		
		public function gotoMenu(menu:String) {
			ExternalInterface.call("gotoMenu", menu);
		}

		public function aFun(evt:MouseEvent) {
			this.gotoMenu("A");
			return;
		}// end function
		
		public function bFun(evt:MouseEvent) {
			this.gotoMenu("B");
			return;
		}// end function
		
		public function cFun(evt:MouseEvent) {
			this.gotoMenu("C");
			return;
		}// end function

		public function dFun(evt:MouseEvent) {
			this.gotoMenu("D");
			return;
		}// end function

		public function eFun(evt:MouseEvent) {
			this.gotoMenu("E");
			return;
		}// end function
	}
}
