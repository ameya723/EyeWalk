package ub.eyewalk.modes;

import ub.eyewalk.states.*;
import ub.eyewalk.menus.*;
import ub.eyewalk.*;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DetectionMode implements IExecutionState, IMenuCallback {
	
	private IExecutionState internalState;
	private ExecutionModule executionMod;
	private Menu resultsReader;
	
	public DetectionMode(ExecutionModule e) {
		executionMod = e;
		executionMod.getOutputProcessor().sayText("Long tap the screen for results");
		setDetectionIdle();
	}
	
	public void detect() {
		
		ArrayList<String> results = new ArrayList<String>();
		MapObject detectedItem = executionMod.getMap().checkCollisions();
		if (detectedItem == null) {
			results.add("Nothing detected");
		} else {
			StringTokenizer s = new StringTokenizer(detectedItem.text,".");
			while (s.hasMoreTokens()) {
				results.add(s.nextToken());
			}
			long dist = Math.round(detectedItem.distanceTo/12);
			String temp;
			if (dist == 1)
				temp = "1 foot away";
			else
				temp = String.valueOf(dist) + " feet away";
			
			results.add(temp);
		}
		
		resultsReader = new Menu("", results, executionMod.getOutputProcessor(), executionMod.getApp(), this, 750);
		internalState = new MenuListenState(resultsReader, this);
		executionMod.setDebugText("Mode = Detection Results\n\nTap screen or press back key to stop");
		resultsReader.startMenu();
		
	}
	
	@Override
	public void shutdown() {
		executionMod.setIdleMode();
	}

	@Override
	public void shortTap() { internalState.shortTap(); }

	@Override
	public void longTap() { internalState.longTap(); }

	@Override
	public void backKeyPressed() { internalState.backKeyPressed(); }

	@Override
	public void appPaused() {
		//TODO - Add detection specifics here
		internalState.appPaused();
	}

	@Override
	public void appResumed() {
		//TODO - Add detection specifics here
		internalState.appResumed();
	}

	@Override
	public void selectionMade(int key) {
		setDetectionIdle();
	}
	
	@Override
	public void menuTimeout() {
		setDetectionIdle();		//Done reading results
	}
	
	private void setDetectionIdle() {
		internalState = new DetectionIdleState(this);
		executionMod.setDebugText("Mode = Detection Idle Mode\n\nLong Tap screen to detect\nBack key to return to idle mode");
	}
	
}
