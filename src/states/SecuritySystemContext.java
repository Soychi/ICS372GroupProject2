package states;

import display.SecuritySystemDisplay;
import events.ZoneUnreadyEvent;

public class SecuritySystemContext {
	private SecuritySystemDisplay display;
	private SecuritySystemState currentState;
	private static SecuritySystemContext instance;
	private boolean zoneOneReady, zoneTwoReady, zoneThreeReady;
	/**
	 * Make a singleton
	 */
	private SecuritySystemContext() {
		instance = this;
		currentState = DisarmedState.instance();
	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static SecuritySystemContext instance() {
		if (instance == null) {
			instance = new SecuritySystemContext();
		}
		return instance;
	}

	/**
	 * The display could change. So we have to get its refrence.
	 * 
	 * @param display The current display object
	 */
	public void setDisplay(SecuritySystemDisplay display) {
		this.display = display;
	}

	/**
	 * Lets the disarmed state be the starting state
	 */
	public void initialize() {
		instance.changeState(DisarmedState.instance());
		zoneOneReady = false;
		zoneTwoReady = false;
		zoneThreeReady = false;
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState the next state
	 */
	public void changeState(SecuritySystemState nextState) {
		currentState.leave();
		currentState = nextState;
		currentState.enter();
	}

	public void handleEvent(ZoneUnreadyEvent event) {
		currentState.handleEvent(event);
	}

	public void showTimeLeft(int time) {
		display.showTimeLeft(time);
	}

	public void showUnready() {
		display.showUnready();
	}

	public void showReady() {
		display.showReady();
	}

	public void showArmed() {
		display.showArmed();
	}

	public void showDisarmed() {
		display.showDisarmed();
	}

}
