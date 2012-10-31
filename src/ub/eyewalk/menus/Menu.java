package ub.eyewalk.menus;

import ub.eyewalk.io.OutputProcessor;

import android.app.Activity;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.os.CountDownTimer;
import java.util.ArrayList;

/* Responsibility - Read menu choices, return result when asked
 *				  - Invoke callback when menu finishes cycling
 */
public class Menu {
	
	public static final int MENU_CANCEL = -1;
	public static final int DEFAULT_TIMEOUT = 2000;
	
	private String menuName;
	private ArrayList<String> menuOptions;
	private OutputProcessor outputProc;
	private Activity app;
	private String objID = "";
	private int currSelection;
	private int timeout;
	private boolean running;
	private IMenuCallback callback;
	private MenuCountdown menuTimer;
	private MenuCountdown nameTimer;
	private TimerStartListener menuTimerListener;
	private TimerStartListener nameTimerListener;
	
	public Menu(String name, ArrayList<String> options, OutputProcessor output, Activity app, IMenuCallback callback) {
		this(name, options, output, app, callback, DEFAULT_TIMEOUT);
	}
	
	public Menu(String name, ArrayList<String> options, OutputProcessor output, Activity app, IMenuCallback callback, int timeout) {
		setMenu(name, options);
		outputProc = output;
		this.app = app;
		this.timeout = timeout;
		objID = String.valueOf(this.hashCode());
		running = false;
		this.callback = callback;
		initTimers();
	}
	
	public void setMenu(String name, ArrayList<String> options) {
		menuName = name;
		menuOptions = options;
		currSelection = MENU_CANCEL;
	}
	
	private void initTimers() {
		nameTimer = new MenuCountdown(1000);
		nameTimerListener = new TimerStartListener(nameTimer);
		menuTimer = new MenuCountdown(timeout);
		menuTimerListener = new TimerStartListener(menuTimer);
	}
	
	public void startMenu() {
		running = true;
		currSelection = MENU_CANCEL;
		if (menuName.length() == 0) {
			nextOption();
		} else {
			outputProc.sayText(menuName, nameTimerListener, objID);
		}
	}
	
	public int getMenuChoice() {
		return currSelection;
	}
	
	public void resumeMenu() {
		running = true;
		if (currSelection == MENU_CANCEL) {
			nameTimer.start();
		} else {
			menuTimer.start();
		}
	}
	
	public void stopMenu() {
		menuTimer.cancel();
		nameTimer.cancel();
		running = false;
	}
	
	/* Callback for the timers to read the next menu choice */
	private void nextOption() {
		if (!running)	//User may have already made selection 
			return;
		
		menuTimer.cancel();
		currSelection++;
		if (currSelection >= menuOptions.size()) {
			currSelection = 0;
			callback.menuTimeout();
		} else {
			String message = menuOptions.get(currSelection);
			outputProc.sayText(message, menuTimerListener, objID);
		}
	}
	
	/* The timers start when the TTS engine is finished speaking */
	private class TimerStartListener implements OnUtteranceCompletedListener {
		
		CountDownTimer timer;
		
		public TimerStartListener(CountDownTimer t) { timer = t; }
		
		@Override
		public void onUtteranceCompleted(String utteranceId) {
			if (objID.equals(utteranceId)) {	//If not, callback is from a different menu object and needs to be ignored.
				app.runOnUiThread(new Runnable() {
		            @Override
		            public void run() {
		            	if (running)	//user may have made selection before
		            		timer.cancel();
		            		timer.start();
		            }
				});
			}
		}
	}
	
	/* Timers that control when the next menu option will be spoken */
	private class MenuCountdown extends CountDownTimer {
		
		public MenuCountdown(long duration) { 
			super(duration, duration); 
		}
		
		@Override
		public void onFinish() {
			nextOption();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			//do nothing
		}
		
	}
	
}
