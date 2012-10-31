package ub.eyewalk.states;

import ub.eyewalk.modes.ExecutionModule;

public class IdleState implements IExecutionState {
	
	private ExecutionModule executionMod;
	
	public IdleState(ExecutionModule e) { 
		executionMod = e; 
	}
	
	@Override
	public void shortTap() { }
	
	@Override
	public void longTap() { executionMod.startMainMenu(); }

	@Override
	public void backKeyPressed() { executionMod.exit(); }

	@Override
	public void appPaused() {  }

	@Override
	public void appResumed() {  }
	
	@Override
	public void shutdown() { }
	
}
