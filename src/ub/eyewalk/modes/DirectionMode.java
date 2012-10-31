package ub.eyewalk.modes;

import java.util.ArrayList;
import ub.eyewalk.states.*;
import ub.eyewalk.menus.*;

public class DirectionMode implements IExecutionState, IMenuCallback {
	
	private ExecutionModule executionMod;
	private Menu resultsReader;
	private IExecutionState internalState;
	private int menuMode = -1;
	private final static int READ_OPTIONS = 0, READ_RESULTS = 1;
	
	public DirectionMode(ExecutionModule e) {
		executionMod = e;
		getDirections();
	}
	
	public void getDirections() {
		executionMod.setDebugText("Mode = Direction Mode\n\nBack key to return to idle mode");

		ArrayList<String> options = new ArrayList<String>();
		options.add("Men's bathroom");
		options.add("Emergency Exit");
		options.add("Baldy 19");
		
		//Read list of destinations
		resultsReader = new Menu("Select a destination", options, executionMod.getOutputProcessor(), executionMod.getApp(), this, 1500);
		internalState = new MenuListenState(resultsReader, this);
		menuMode = READ_OPTIONS;
		executionMod.setDebugText("Mode = Directions Options\n\nTap screen or press back key to stop");
		resultsReader.startMenu();
		
		//Wait for menu callback when a selection is made...
		
	}
	
	public void readDirections() {
		
		internalState = null;	//Calculating
		
		ArrayList<String> directions = new ArrayList<String>();
		directions.add("In 10 feet turn left.");
		directions.add("Destination in 200 feet on left.");
		
		resultsReader = new Menu("Directions to Baldy 19" , directions, executionMod.getOutputProcessor(), executionMod.getApp(), this);
		menuMode = READ_RESULTS;
		internalState = new MenuListenState(resultsReader, this);
		executionMod.setDebugText("Mode = Directions Results" + "\n\nPress any key to return to Idle Mode");
		resultsReader.startMenu();
	}
	
	@Override
	public void shutdown() {
		executionMod.setIdleMode();
	}

	/*******************************	MENU CALLBACKS	*******************************/
	
	@Override
	public void selectionMade(int key) {
		
		if (menuMode == READ_OPTIONS && key != Menu.MENU_CANCEL) {
				//TODO Get directions here
				readDirections();
		} else {
			executionMod.setIdleMode();
		}
	}

	@Override
	public void menuTimeout() {
		executionMod.setIdleMode();
	}
	
	/*******************************	UI CALLBACKS	*******************************/
	
	@Override
	public void shortTap() {
		if (internalState != null) {
			internalState.shortTap();
		}
	}

	@Override
	public void longTap() {
		if (internalState != null) {
			internalState.longTap();
		}
	}

	@Override
	public void backKeyPressed() {
		if (internalState != null) {
			internalState.backKeyPressed();
		}
	}

	@Override
	public void appPaused() {
		if (internalState != null) {
			internalState.appPaused();
		}
	}

	@Override
	public void appResumed() {
		if (internalState != null) {
			internalState.appResumed();
		}
	}
	
}
