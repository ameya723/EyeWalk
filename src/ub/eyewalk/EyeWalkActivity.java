package ub.eyewalk;

import ub.eyewalk.modes.ExecutionModule;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class EyeWalkActivity extends Activity implements OnClickListener, OnLongClickListener {
    
	ExecutionModule mainMod;
	DrawingSurfaceView dsv;
	   
    private SensorManager mSensorManager;
    float[] magnitude_values;
    float[] accelerometer_values;
    boolean sensorReady;
	
    private final SensorEventListener mListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {            
        	
        	switch (event.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnitude_values = event.values.clone();
                sensorReady = true;
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometer_values = event.values.clone();
            }

            if (magnitude_values != null && accelerometer_values != null && sensorReady) {
                sensorReady = false;
                
                float[] R = new float[16];
                float[] I = new float[16];

                SensorManager.getRotationMatrix(R, I, accelerometer_values, magnitude_values);

                float[] actual_orientation = new float[3];
                SensorManager.getOrientation(R, actual_orientation);
                
                dsv.draw(actual_orientation[0]);
                
            }
        }
        
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        dsv = new DrawingSurfaceView(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainScreen);
        rl.addView(dsv);
        //rl.addView(dsv.tv);
        
    	mainMod = new ExecutionModule(this, dsv);
    	
    	findViewById(R.id.mainScreen).setOnClickListener(this);
    	findViewById(R.id.mainScreen).setOnLongClickListener(this);
        
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
    }
    
    @Override
    public void onDestroy() {
    	mainMod.shutdown();
    	super.onDestroy();
    }
    
    @Override
    public void onPause() {
    	mSensorManager.unregisterListener(mListener);
    	mainMod.appPaused();
    	super.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        
    	mainMod.appResumed();
    }
    
    @Override 
    public void onStart() {
    	super.onStart();
    	mainMod.start();
    }
    
    @Override 
    public void onStop() {
    	mSensorManager.unregisterListener(mListener);
    	super.onStop();
    }
    
    @Override public void onBackPressed () { mainMod.backKeyPressed(); }
    
	@Override
	public void onClick(View v) { mainMod.shortTap(); }

	@Override
	public boolean onLongClick(View v) {
		mainMod.longTap();
		return true;	//Consume long click event
	}
    
}