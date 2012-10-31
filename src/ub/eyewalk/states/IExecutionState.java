package ub.eyewalk.states;

public interface IExecutionState {
	
	/* Callback for screen short click events */
	public void shortTap();
	
	/* Callback for screen long click events */
	public void longTap();
	
	/* Callback for back key press events */
	public void backKeyPressed();
	
	/* Callback for Activity onPause() */
	public void appPaused();
	
	/* Callback for Activity onResume() */
	public void appResumed();
	
	/* Callback when application finishes*/
	public void shutdown();
	
}
