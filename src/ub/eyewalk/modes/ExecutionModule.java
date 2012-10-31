package ub.eyewalk.modes;

import ub.eyewalk.*;
import ub.eyewalk.io.*;
import ub.eyewalk.menus.IMenuCallback;
import ub.eyewalk.menus.Menu;
import ub.eyewalk.states.*;

import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

public class ExecutionModule implements IMenuCallback, IExecutionState {
	
	private Activity app;
	private OutputProcessor output;
	private IExecutionState currState;
	private ArrayList<String> mainMenuText;
	private Menu mainMenu;
	private TextView debugText;
	private DrawingSurfaceView map;
	
	private static final int MAIN_MENU_DETECTION = 0;
	private static final int MAIN_MENU_DIRECTION = 1;
	private static final int MAIN_MENU_CONFIGURATION = 2;
	private static final int MAIN_MENU_EXIT = 3;
	
	public ExecutionModule(Activity a, DrawingSurfaceView dsv) {
		app = a;
		output = new OutputProcessor(app);
		map = dsv;
		debugText = (TextView) app.findViewById(R.id.debugText);
		buildMainMenu();
		setIdleMode();
	}
	
	/**********************  Service Providers ***********************/
	
	public OutputProcessor getOutputProcessor() { return output; }
	
	public Activity getApp() { return app; }
	
	public DrawingSurfaceView getMap() { return map; }
	
	public void setDebugText(String text) {	
		debugText.setText(text); 
	}
	
	public void setIdleMode() { 
		currState = new IdleState(this);
		this.setDebugText("Mode = Idle Mode\n\nLong tap to start menu\nPress back key to exit system");
	}
	
	/***********************  Input Callbacks  ***********************/
	
	@Override
	/* Callback from input processor for regular click event */
	public void shortTap() { 
		map.move();
		currState.shortTap(); 
	}

	@Override
	/* Callback from input processor for long-click event */
	public void longTap() { currState.longTap(); }

	@Override
	/* Callback from Activity for back press event */
	public void backKeyPressed() { currState.backKeyPressed(); }

	/***********************  App State Callbacks  ***********************/
	
	@Override
	public void appPaused() { currState.appPaused(); }
	
	@Override
	public void appResumed() { currState.appResumed(); }
	
	@Override
	/* Called when the activity is destroyed */
	public void shutdown() {
		output.shutdown();		//close the speech engine
		currState.shutdown();
	}
	
	/***********************  Main Menu Methods  ***********************/
	
	private void buildMainMenu() { 
		mainMenuText = new ArrayList<String>();
		mainMenuText.add("Detect Objects");
		mainMenuText.add("Get Directions");
		mainMenuText.add("Configuration");
		mainMenuText.add("Exit System");
		mainMenu = new Menu("Main Menu", mainMenuText, output, app, this);
	}
	
	public void startMainMenu() { 
		currState = new MenuListenState(mainMenu, this);
		this.setDebugText("Mode = Menu Mode\n\nTap screen to select mode\nBack key to exit");
		mainMenu.startMenu();
	}
	
	@Override	//IMenuCallback
	public void menuTimeout() { setIdleMode(); }
	
	/***********************  Mode Selection Methods  ***********************/
	
	@Override	//IMenuCallback
	public void selectionMade(int key) {
		switch (key) {
		case ExecutionModule.MAIN_MENU_DETECTION:
			startDetectionMode();
			break;
		case ExecutionModule.MAIN_MENU_DIRECTION:
			startDirectionMode();
			break;
		case ExecutionModule.MAIN_MENU_CONFIGURATION:
			startConfigurationMode();
			break;
		case ExecutionModule.MAIN_MENU_EXIT:
			exit();
			break;
		case Menu.MENU_CANCEL:
			currState = new IdleState(this);
			break;
		default:
			currState = new IdleState(this);
			break;
		}
	}
	
	public void startDetectionMode() {
		currState = new DetectionMode(this);
	}
	
	public void startDirectionMode() {
		//output.sayText("Starting direction mode");
		currState = new DirectionMode(this);
	}
	
	public void startConfigurationMode() {
		//TODO - Change state, run Configuration
		output.sayText("Starting configuration mode");
		setIdleMode();
	}
	
	public void exit() { app.finish(); }
	
	public void start() {
		StartThread s = new StartThread();
		s.start();
	}
	
	class StartThread extends Thread {
		
		@Override
		public void run() {
			while (!output.isInitialized()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					app.finish();
				}
			}
			output.sayText("Map of Baldy Basement is now loaded.");
		}
	}
	
}