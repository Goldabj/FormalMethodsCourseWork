package client;

import org.yakindu.scr.callhandling.ICallHandlingStatemachine;
import org.yakindu.scr.callhandling.ICallHandlingStatemachine.SCIUserOperationCallback;

public class UserCallBack implements SCIUserOperationCallback {

	
	@Override
	public void speak() {
		System.out.println("Blah Blah Blah");
	}
}
