package client;

import org.yakindu.scr.elevatormodeling.IElevatorModelingStatemachine.SCIElevatorOperationCallback;

public class ElevatorCallback implements SCIElevatorOperationCallback {

	@Override
	public void up(long start, long end) {
		for (long i = start; i < end; i++) {
			System.out.println("Going Up: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("Arrived " + end); 
		
	}

	@Override
	public void down(long start, long end) {
		for (long i = start; i > end; i--) {
			System.out.println("Going Down: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Arrived " + end); 
		
	}

}
