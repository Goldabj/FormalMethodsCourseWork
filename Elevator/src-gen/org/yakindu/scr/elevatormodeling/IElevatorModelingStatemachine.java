package org.yakindu.scr.elevatormodeling;

import org.yakindu.scr.IStatemachine;

public interface IElevatorModelingStatemachine extends IStatemachine {

	public interface SCIElevator {
	
		public void raiseButton1Pressed();
		
		public void raiseButton2Pressed();
		
		public void raiseButton3Pressed();
		
		public void raiseEmergencyStopPressed();
		
		public long getCurrentFloor();
		
		public void setCurrentFloor(long value);
		
		public void setSCIElevatorOperationCallback(SCIElevatorOperationCallback operationCallback);
	
	}
	
	public interface SCIElevatorOperationCallback {
	
		public void up(long start, long end);
		
		public void down(long start, long end);
		
	}
	
	public SCIElevator getSCIElevator();
	
	public interface SCIRepairMan {
	
		public void raiseFixElevator();
		
	}
	
	public SCIRepairMan getSCIRepairMan();
	
}
