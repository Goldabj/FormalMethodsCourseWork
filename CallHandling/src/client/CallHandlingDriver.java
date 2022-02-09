package client;

import org.yakindu.scr.TimerService;
import org.yakindu.scr.callhandling.CallHandlingStatemachine;
import org.yakindu.scr.callhandling.ICallHandlingStatemachine;

public class CallHandlingDriver {

	public static void main(String[] args) throws InterruptedException {

		CallHandlingStatemachine sm = new CallHandlingStatemachine();
		ICallHandlingStatemachine.SCIUser user = sm.getSCIUser();
		ICallHandlingStatemachine.SCIPhone phone = sm.getSCIPhone();

		user.setSCIUserOperationCallback(new UserCallBack());
		phone.setSCIPhoneOperationCallback(new PhoneCallback());

		sm.setTimer(new TimerService());

		sm.init();
		sm.enter();

		phone.raiseIncomingCall();
		for (int i = 0; i < 30; ++i) {
			sm.runCycle();
			Thread.sleep(200);
		}

		user.raiseAcceptCall();
		for (int i = 0; i < 30; i++) {
			sm.runCycle();
			Thread.sleep(200);
		}


		user.raiseDismiseCall();
		sm.runCycle();

		sm.exit();

	}

}
