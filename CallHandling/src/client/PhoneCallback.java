package client;

import javax.swing.Icon;

import org.yakindu.scr.callhandling.ICallHandlingStatemachine;
import org.yakindu.scr.callhandling.ICallHandlingStatemachine.SCIPhoneOperationCallback;

public class PhoneCallback implements SCIPhoneOperationCallback{
	
	@Override
	public void ring(long count) {
		System.out.println("Ring ring" + count);
	}

}
