package org.yakindu.scr.elevatormodeling;

public class ElevatorModelingStatemachine implements IElevatorModelingStatemachine {

	protected class SCIElevatorImpl implements SCIElevator {
	
		private SCIElevatorOperationCallback operationCallback;
		
		public void setSCIElevatorOperationCallback(
				SCIElevatorOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean button1Pressed;
		
		public void raiseButton1Pressed() {
			button1Pressed = true;
		}
		
		private boolean button2Pressed;
		
		public void raiseButton2Pressed() {
			button2Pressed = true;
		}
		
		private boolean button3Pressed;
		
		public void raiseButton3Pressed() {
			button3Pressed = true;
		}
		
		private boolean emergencyStopPressed;
		
		public void raiseEmergencyStopPressed() {
			emergencyStopPressed = true;
		}
		
		private long currentFloor;
		
		public long getCurrentFloor() {
			return currentFloor;
		}
		
		public void setCurrentFloor(long value) {
			this.currentFloor = value;
		}
		
		protected void clearEvents() {
			button1Pressed = false;
			button2Pressed = false;
			button3Pressed = false;
			emergencyStopPressed = false;
		}
	}
	
	protected SCIElevatorImpl sCIElevator;
	
	protected class SCIRepairManImpl implements SCIRepairMan {
	
		private boolean fixElevator;
		
		public void raiseFixElevator() {
			fixElevator = true;
		}
		
		protected void clearEvents() {
			fixElevator = false;
		}
	}
	
	protected SCIRepairManImpl sCIRepairMan;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Elevator,
		main_region_Elevator_In_Service_Floor1,
		main_region_Elevator_In_Service_Floor2,
		main_region_Elevator_In_Service_Floor3,
		main_region_OutOfService,
		$NullState$
	};
	
	private State[] historyVector = new State[1];
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public ElevatorModelingStatemachine() {
		sCIElevator = new SCIElevatorImpl();
		sCIRepairMan = new SCIRepairManImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		for (int i = 0; i < 1; i++) {
			historyVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCIElevator.setCurrentFloor(0);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_main_region_default();
	}
	
	public void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCIElevator.clearEvents();
		sCIRepairMan.clearEvents();
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_Elevator:
			return stateVector[0].ordinal() >= State.
					main_region_Elevator.ordinal()&& stateVector[0].ordinal() <= State.main_region_Elevator_In_Service_Floor3.ordinal();
		case main_region_Elevator_In_Service_Floor1:
			return stateVector[0] == State.main_region_Elevator_In_Service_Floor1;
		case main_region_Elevator_In_Service_Floor2:
			return stateVector[0] == State.main_region_Elevator_In_Service_Floor2;
		case main_region_Elevator_In_Service_Floor3:
			return stateVector[0] == State.main_region_Elevator_In_Service_Floor3;
		case main_region_OutOfService:
			return stateVector[0] == State.main_region_OutOfService;
		default:
			return false;
		}
	}
	
	public SCIElevator getSCIElevator() {
		return sCIElevator;
	}
	
	public SCIRepairMan getSCIRepairMan() {
		return sCIRepairMan;
	}
	
	private boolean check_main_region_Elevator_tr0_tr0() {
		return sCIElevator.emergencyStopPressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor1_tr0_tr0() {
		return sCIElevator.button2Pressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor1_tr1_tr1() {
		return sCIElevator.button3Pressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor2_tr0_tr0() {
		return sCIElevator.button1Pressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor2_tr1_tr1() {
		return sCIElevator.button3Pressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor3_tr0_tr0() {
		return sCIElevator.button1Pressed;
	}
	
	private boolean check_main_region_Elevator_In_Service_Floor3_tr1_tr1() {
		return sCIElevator.button2Pressed;
	}
	
	private boolean check_main_region_OutOfService_tr0_tr0() {
		return sCIRepairMan.fixElevator;
	}
	
	private void effect_main_region_Elevator_tr0() {
		exitSequence_main_region_Elevator();
		enterSequence_main_region_OutOfService_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor1_tr0() {
		exitSequence_main_region_Elevator_In_Service_Floor1();
		sCIElevator.operationCallback.up(1, 2);
		
		enterSequence_main_region_Elevator_In_Service_Floor2_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor1_tr1() {
		exitSequence_main_region_Elevator_In_Service_Floor1();
		sCIElevator.operationCallback.up(1, 3);
		
		enterSequence_main_region_Elevator_In_Service_Floor3_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor2_tr0() {
		exitSequence_main_region_Elevator_In_Service_Floor2();
		sCIElevator.operationCallback.down(2, 1);
		
		enterSequence_main_region_Elevator_In_Service_Floor1_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor2_tr1() {
		exitSequence_main_region_Elevator_In_Service_Floor2();
		sCIElevator.operationCallback.up(2, 3);
		
		enterSequence_main_region_Elevator_In_Service_Floor3_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor3_tr0() {
		exitSequence_main_region_Elevator_In_Service_Floor3();
		sCIElevator.operationCallback.down(3, 1);
		
		enterSequence_main_region_Elevator_In_Service_Floor1_default();
	}
	
	private void effect_main_region_Elevator_In_Service_Floor3_tr1() {
		exitSequence_main_region_Elevator_In_Service_Floor3();
		sCIElevator.operationCallback.down(3, 2);
		
		enterSequence_main_region_Elevator_In_Service_Floor2_default();
	}
	
	private void effect_main_region_OutOfService_tr0() {
		exitSequence_main_region_OutOfService();
		react_main_region_Elevator_In_Service__entry_Default();
	}
	
	/* Entry action for state 'Floor1'. */
	private void entryAction_main_region_Elevator_In_Service_Floor1() {
		sCIElevator.setCurrentFloor(1);
	}
	
	/* Entry action for state 'Floor2'. */
	private void entryAction_main_region_Elevator_In_Service_Floor2() {
		sCIElevator.setCurrentFloor(2);
	}
	
	/* Entry action for state 'Floor3'. */
	private void entryAction_main_region_Elevator_In_Service_Floor3() {
		sCIElevator.setCurrentFloor(3);
	}
	
	/* 'default' enter sequence for state Elevator */
	private void enterSequence_main_region_Elevator_default() {
		enterSequence_main_region_Elevator_In_Service_default();
	}
	
	/* 'default' enter sequence for state Floor1 */
	private void enterSequence_main_region_Elevator_In_Service_Floor1_default() {
		entryAction_main_region_Elevator_In_Service_Floor1();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Elevator_In_Service_Floor1;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state Floor2 */
	private void enterSequence_main_region_Elevator_In_Service_Floor2_default() {
		entryAction_main_region_Elevator_In_Service_Floor2();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Elevator_In_Service_Floor2;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state Floor3 */
	private void enterSequence_main_region_Elevator_In_Service_Floor3_default() {
		entryAction_main_region_Elevator_In_Service_Floor3();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Elevator_In_Service_Floor3;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state OutOfService */
	private void enterSequence_main_region_OutOfService_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_OutOfService;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region In Service */
	private void enterSequence_main_region_Elevator_In_Service_default() {
		react_main_region_Elevator_In_Service__entry_Default();
	}
	
	/* shallow enterSequence with history in child In Service */
	private void shallowEnterSequence_main_region_Elevator_In_Service() {
		switch (historyVector[0]) {
		case main_region_Elevator_In_Service_Floor1:
			enterSequence_main_region_Elevator_In_Service_Floor1_default();
			break;
		case main_region_Elevator_In_Service_Floor2:
			enterSequence_main_region_Elevator_In_Service_Floor2_default();
			break;
		case main_region_Elevator_In_Service_Floor3:
			enterSequence_main_region_Elevator_In_Service_Floor3_default();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for state Elevator */
	private void exitSequence_main_region_Elevator() {
		exitSequence_main_region_Elevator_In_Service();
	}
	
	/* Default exit sequence for state Floor1 */
	private void exitSequence_main_region_Elevator_In_Service_Floor1() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Floor2 */
	private void exitSequence_main_region_Elevator_In_Service_Floor2() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Floor3 */
	private void exitSequence_main_region_Elevator_In_Service_Floor3() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state OutOfService */
	private void exitSequence_main_region_OutOfService() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Elevator_In_Service_Floor1:
			exitSequence_main_region_Elevator_In_Service_Floor1();
			break;
		case main_region_Elevator_In_Service_Floor2:
			exitSequence_main_region_Elevator_In_Service_Floor2();
			break;
		case main_region_Elevator_In_Service_Floor3:
			exitSequence_main_region_Elevator_In_Service_Floor3();
			break;
		case main_region_OutOfService:
			exitSequence_main_region_OutOfService();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region In Service */
	private void exitSequence_main_region_Elevator_In_Service() {
		switch (stateVector[0]) {
		case main_region_Elevator_In_Service_Floor1:
			exitSequence_main_region_Elevator_In_Service_Floor1();
			break;
		case main_region_Elevator_In_Service_Floor2:
			exitSequence_main_region_Elevator_In_Service_Floor2();
			break;
		case main_region_Elevator_In_Service_Floor3:
			exitSequence_main_region_Elevator_In_Service_Floor3();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state Floor1. */
	private void react_main_region_Elevator_In_Service_Floor1() {
		if (check_main_region_Elevator_tr0_tr0()) {
			effect_main_region_Elevator_tr0();
		} else {
			if (check_main_region_Elevator_In_Service_Floor1_tr0_tr0()) {
				effect_main_region_Elevator_In_Service_Floor1_tr0();
			} else {
				if (check_main_region_Elevator_In_Service_Floor1_tr1_tr1()) {
					effect_main_region_Elevator_In_Service_Floor1_tr1();
				}
			}
		}
	}
	
	/* The reactions of state Floor2. */
	private void react_main_region_Elevator_In_Service_Floor2() {
		if (check_main_region_Elevator_tr0_tr0()) {
			effect_main_region_Elevator_tr0();
		} else {
			if (check_main_region_Elevator_In_Service_Floor2_tr0_tr0()) {
				effect_main_region_Elevator_In_Service_Floor2_tr0();
			} else {
				if (check_main_region_Elevator_In_Service_Floor2_tr1_tr1()) {
					effect_main_region_Elevator_In_Service_Floor2_tr1();
				}
			}
		}
	}
	
	/* The reactions of state Floor3. */
	private void react_main_region_Elevator_In_Service_Floor3() {
		if (check_main_region_Elevator_tr0_tr0()) {
			effect_main_region_Elevator_tr0();
		} else {
			if (check_main_region_Elevator_In_Service_Floor3_tr0_tr0()) {
				effect_main_region_Elevator_In_Service_Floor3_tr0();
			} else {
				if (check_main_region_Elevator_In_Service_Floor3_tr1_tr1()) {
					effect_main_region_Elevator_In_Service_Floor3_tr1();
				}
			}
		}
	}
	
	/* The reactions of state OutOfService. */
	private void react_main_region_OutOfService() {
		if (check_main_region_OutOfService_tr0_tr0()) {
			effect_main_region_OutOfService_tr0();
		}
	}
	
	/* Default react sequence for shallow history entry  */
	private void react_main_region_Elevator_In_Service__entry_Default() {
		/* Enter the region with shallow history */
		if (historyVector[0] != State.$NullState$) {
			shallowEnterSequence_main_region_Elevator_In_Service();
		} else {
			enterSequence_main_region_Elevator_In_Service_Floor1_default();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Elevator_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_Elevator_In_Service_Floor1:
				react_main_region_Elevator_In_Service_Floor1();
				break;
			case main_region_Elevator_In_Service_Floor2:
				react_main_region_Elevator_In_Service_Floor2();
				break;
			case main_region_Elevator_In_Service_Floor3:
				react_main_region_Elevator_In_Service_Floor3();
				break;
			case main_region_OutOfService:
				react_main_region_OutOfService();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
