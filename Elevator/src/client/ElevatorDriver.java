package client;

import org.yakindu.scr.elevatormodeling.ElevatorModelingStatemachine;
import org.yakindu.scr.elevatormodeling.IElevatorModelingStatemachine;
import org.yakindu.scr.elevatormodeling.IElevatorModelingStatemachine.SCIElevatorOperationCallback;

public class ElevatorDriver {

	public static void main(String[] args) {
		
		ElevatorModelingStatemachine sm = new ElevatorModelingStatemachine();
		IElevatorModelingStatemachine.SCIElevator elevator = sm.getSCIElevator();
		
		elevator.setSCIElevatorOperationCallback(new ElevatorCallback());
		
		sm.init();
		sm.enter();
		
		sm.runCycle();
		
		elevator.raiseButton2Pressed();
		sm.runCycle();
		
		elevator.raiseButton3Pressed();
		sm.runCycle();
		
		elevator.raiseButton2Pressed();
		sm.runCycle();
		
		elevator.raiseButton1Pressed();
		sm.runCycle();
		
		elevator.raiseButton3Pressed();
		sm.runCycle();
		
		elevator.raiseButton1Pressed();
		sm.runCycle();
		
		elevator.raiseEmergencyStopPressed();
		sm.runCycle();
		
		elevator.raiseButton3Pressed();
		sm.runCycle(); 
		
		sm.getSCIRepairMan().raiseFixElevator();
		sm.runCycle();
		
		elevator.raiseButton3Pressed();
		sm.runCycle();
		
		sm.exit();

	}

}
