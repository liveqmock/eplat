package {
	import flash.display.*;
	import flash.events.*;
	import flash.external.*;

	public class ExitSystem extends MovieClip {
		public var CloseBtn:SimpleButton;

		public function ExitSystem() {
			addFrameScript(0, frame1);
			return;
		}// end function

		function frame1() {
			CloseBtn.addEventListener(MouseEvent.MOUSE_DOWN, onExitSystem);
			return;
		}// end function

		public function onExitSystem(evt:MouseEvent) {
			ExternalInterface.call("onExitSystem");
			return;
		}// end function

	}
}