package ub.eyewalk.io;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;

public class OutputProcessor implements OnInitListener {
	
	private TextToSpeech speechEngine ;
	private boolean init = false;
	
	public OutputProcessor(Activity owner) {
		speechEngine = new TextToSpeech(owner, this);
	}
	
	/*
	 * TTS engine speaks the text provided
	 * A callback is made to the provided listener when the speech is complete
	 */
	public int sayText(String text, OnUtteranceCompletedListener callback, String callbackID) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, callbackID);
		speechEngine.setOnUtteranceCompletedListener(callback);
		return speechEngine.speak(text, TextToSpeech.QUEUE_ADD, params);
	}
	
	/*
	 * TTS engine speaks the text provided
	 * NO callbacks are made
	 */
	public int sayText(String text) {
		return speechEngine.speak(text, TextToSpeech.QUEUE_ADD, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.speech.tts.TextToSpeech.OnInitListener#onInit(int)
	 * 
	 * Required callback for initialization
	 *  - Set locale to US
	 *  - Log failures
	 *  - Language configuration
	 */
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = speechEngine.setLanguage(Locale.US);
			
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("eyeWalk", "Language is not available.");
			} else {
				//Initialized properly
			}
		} else {
			// Initialization failed
			Log.e("eyeWalk", "Could not initialize TextToSpeech.");
		}
		init = true;
	}
	
	public void shutdown() {
		speechEngine.shutdown();
	}
	
	public boolean isInitialized() { return init; } 
	
}
