package ub.eyewalk.states;

import ub.eyewalk.modes.DetectionMode;

public class DetectionIdleState implements IExecutionState {
	
	private DetectionMode detection;
	
	public DetectionIdleState(DetectionMode d) { detection = d; }
	
	@Override
	public void shortTap() { 
		//Nothing
	}

	@Override
	public void longTap() { detection.detect(); }

	@Override
	public void backKeyPressed() {
		detection.shutdown();	//Exit detection mode
	}

	@Override
	public void appPaused() {
		// TODO - ???
	}

	@Override
	public void appResumed() {
		// TODO - ???
	}

	@Override
	public void shutdown() { }
}
